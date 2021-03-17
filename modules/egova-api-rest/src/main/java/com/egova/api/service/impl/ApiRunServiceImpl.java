package com.egova.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egova.api.convert.ParamConverter;
import com.egova.api.domain.InfoRepository;
import com.egova.api.entity.Info;
import com.egova.api.enums.RequestBodyType;
import com.egova.api.enums.RequestParamType;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.service.ApiRunService;
import com.egova.exception.ExceptionUtils;
import com.egova.lang.ExtrasHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Priority;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
@Slf4j
@Priority(5)
@RequiredArgsConstructor
public class ApiRunServiceImpl implements ApiRunService {
    private final InfoRepository infoRepository;

    @Override
    public String run(String apiId, ApiInfoModel model) {
        Info apiInfo = Optional.ofNullable(infoRepository.getById(apiId)).orElseThrow(() -> ExceptionUtils.api("api不存在"));

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
        if (!CollectionUtils.isEmpty(model.getRequestParams())) {
            model.getRequestParams().stream().filter(p -> p.getType() == RequestParamType.QueryString)
                    .forEach(requestParam -> {
                        queryParamMap.put(requestParam.getName(), requestParam.getValueContent());
                    });
            model.getRequestParams().stream().filter(p -> p.getType() == RequestParamType.FormData)
                    .forEach(requestParam -> {
                        formParamMap.put(requestParam.getName(), requestParam.getValueContent());
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

        // write response
        String responseContent = remoteCall(remoteRequest);
        return responseContent;
    }

    @Override
    public Object outputFormat(String apiId) {
        return null;
    }

    private String markGetUrl(String url, Map<String, Object> queryParamMap) {
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

    private void wrapBodyParams(HttpEntityEnclosingRequestBase httpEntity, RequestBodyType requestBodyType, Map<String, Object> formParamMap, String json) {
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
//            String bodyParams = JSON.toJSONString(json);
            try {
                httpEntity.setEntity(new StringEntity(s));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private String remoteCall(HttpRequestBase remoteRequest){
        // remote test
        String responseContent = null;

        CloseableHttpClient httpClient = null;
        try{
            org.apache.http.client.config.RequestConfig requestConfig = org.apache.http.client.config.RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            remoteRequest.setConfig(requestConfig);

            httpClient = HttpClients.custom().disableAutomaticRetries().build();

            // parse response
            HttpResponse response = httpClient.execute(remoteRequest);
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
}
