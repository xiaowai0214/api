package com.egova.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egova.api.convert.ParamConverter;
import com.egova.api.entity.Info;
import com.egova.api.enums.RequestBodyType;
import com.egova.api.enums.RequestParamType;
import com.egova.api.model.ApiInfoModel;
import com.egova.lang.ExtrasHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
public class HttpUtils {


    public static HttpRequestBase wrapHttpRequest(ApiInfoModel model){
        Info apiInfo = model.getInfo();

        // request headers
        List<Map<String, Object>> requestHeaders = new ArrayList<>();
        Map<String, String> requestHeaderMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(model.getRequestHeaders())) {
            model.getRequestHeaders().forEach(header -> {
                requestHeaders.add(ExtrasHashMap.newStringMap().add(header.getName(), header.getValue()));
                requestHeaderMap.put(header.getName(), header.getValue());
            });
        }

        // query param
        Map<String, Object> queryParamMap = new HashMap<>();
        Map<String, Object> formParamMap = new HashMap<>();
        List<Map<String, Object>> queryParams = new ArrayList<>();
        if (!CollectionUtils.isEmpty(model.getQueryParams())) {
            model.getQueryParams().stream().filter(p -> BooleanUtils.isFalse(p.isDisabled()) && p.getType() == RequestParamType.QueryString)
                    .forEach(requestParam -> {
                        queryParamMap.put(requestParam.getName(), requestParam.getValueContent());
                    });
        }

        if (!CollectionUtils.isEmpty(model.getFormParams())) {
            model.getFormParams().stream().filter(p -> BooleanUtils.isFalse(p.isDisabled()) && p.getType() == RequestParamType.FormData)
                    .forEach(requestParam -> {
                        formParamMap.put(requestParam.getName(), requestParam);
                    });
        }

        // invoke 1/3
        HttpRequestBase remoteRequest = null;
        switch (apiInfo.getMethod()) {
            case POST:
                HttpPost httpPost = new HttpPost(markGetUrl(apiInfo.getUrl(), queryParamMap));
                wrapBodyParams(httpPost, apiInfo.getRequestBodyType(), formParamMap, model.getJson());
                remoteRequest = httpPost;
                break;

            case GET:
                remoteRequest = new HttpGet(markGetUrl(apiInfo.getUrl(), queryParamMap));
                break;

            case PUT:
                HttpPut httpPut = new HttpPut(markGetUrl(apiInfo.getUrl(), queryParamMap));
                wrapBodyParams(httpPut, apiInfo.getRequestBodyType(), formParamMap, model.getJson());
                remoteRequest = httpPut;
                break;
            case DELETE:
                remoteRequest = new HttpDelete(markGetUrl(apiInfo.getUrl(), queryParamMap));
                break;

        }

        if (requestHeaderMap != null && !requestHeaderMap.isEmpty()) {
            for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
                remoteRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return remoteRequest;
    }

    public static String remoteCall(ApiInfoModel model){
        HttpRequestBase remoteRequest = wrapHttpRequest(model);

        // remote test
        String responseContent = null;

        CloseableHttpClient httpClient = null;
        try{
            org.apache.http.client.config.RequestConfig requestConfig = org.apache.http.client.config.RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            remoteRequest.setConfig(requestConfig);

            httpClient = HttpClients.custom().disableAutomaticRetries().build();

            // parse response
            HttpResponse response = httpClient.execute(remoteRequest);
            Header[] allHeaders = response.getAllHeaders();
            Arrays.stream(allHeaders).forEach(header -> System.out.println(header.getName() + ":" + header.getValue() ));

            HttpEntity entity = response.getEntity();
            if (null != entity) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    responseContent = EntityUtils.toString(entity, "UTF-8");
                } else {
                    responseContent = "请求状态异常：" + response.getStatusLine().getStatusCode();
                    if (statusCode==302 && response.getFirstHeader("Location")!=null) {
                        responseContent += "；Redirect地址：" + response.getFirstHeader("Location").getValue();
                    }

                }
                EntityUtils.consume(entity);
            }
            log.info("http statusCode error, statusCode:" + response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            responseContent = "请求异常：" + e.toString();
        } finally{
            if (remoteRequest!=null) {
                remoteRequest.releaseConnection();
            }
            if (httpClient!=null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return responseContent;
    }

    public static String markGetUrl(String url, Map<String, Object> queryParamMap) {
        String finalUrl = url;
        if (queryParamMap != null && queryParamMap.size() > 0) {
            finalUrl = url + "?";
            for (Map.Entry<String, Object> entry : queryParamMap.entrySet()) {
                finalUrl += entry.getKey() + "=" + ParamConverter.convert(entry.getValue().toString()) + "&";
            }
            finalUrl = finalUrl.substring(0, finalUrl.length() - 1);    // 后缀处理，去除 ？ 或 & 符号
        }
        return finalUrl;

    }


    public static void wrapBodyParams(HttpEntityEnclosingRequestBase httpEntity, RequestBodyType requestBodyType, Map<String, Object> formParamMap, String json) {
        // form params
        if (requestBodyType == RequestBodyType.Form) {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            formParamMap.forEach((k, v) -> formParams.add(new BasicNameValuePair(k, v.toString())));
            try {
//                httpEntity.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
                httpEntity.setEntity(new StringEntity(ParamConverter.convert(JSON.toJSONString(formParamMap))));

            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }

        } else {
            //body params
            JSONObject jsonObject = JSONObject.parseObject(json);
            String s = jsonObject.toJSONString();
            s = ParamConverter.convert(s);
            String bodyParams = JSON.toJSONString(json);
            try {
                httpEntity.setEntity(new StringEntity(s));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
