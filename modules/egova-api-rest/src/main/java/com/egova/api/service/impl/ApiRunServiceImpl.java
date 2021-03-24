package com.egova.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egova.api.convert.ParamConverter;
import com.egova.api.domain.FieldMappingRepository;
import com.egova.api.entity.FieldMapping;
import com.egova.api.entity.Info;
import com.egova.api.entity.RequestParam;
import com.egova.api.enums.DataType;
import com.egova.api.enums.RequestBodyType;
import com.egova.api.enums.RequestParamType;
import com.egova.api.facade.InfoFacade;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.model.ApiResult;
import com.egova.api.service.ApiRunService;
import com.egova.api.util.JsonPathUtils;
import com.egova.exception.ExceptionUtils;
import com.egova.lang.ExtrasHashMap;
import com.flagwind.commons.Monment;
import lombok.RequiredArgsConstructor;
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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Priority;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
@Priority(5)
@RequiredArgsConstructor
public class ApiRunServiceImpl implements ApiRunService {
//    private final InfoRepository infoRepository;
    private final InfoFacade infoFacade;
    private final FieldMappingRepository fieldMappingRepository;

    private HttpRequestBase wrapHttpRequest(String apiId, ApiInfoModel model){
        Info apiInfo = Optional.ofNullable(infoFacade.seekById(apiId)).orElseThrow(() -> ExceptionUtils.api("api不存在"));

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
            model.getRequestParams().stream().filter(p -> BooleanUtils.isFalse(p.isDisabled()) && p.getType() == RequestParamType.QueryString)
                    .forEach(requestParam -> {
                        queryParamMap.put(requestParam.getName(), transform(requestParam.getValueContent(),requestParam.getValueType()));
                    });
            model.getRequestParams().stream().filter(p -> BooleanUtils.isFalse(p.isDisabled()) && p.getType() == RequestParamType.FormData)
                    .forEach(requestParam -> {
                        formParamMap.put(requestParam.getName(), transform(requestParam.getValueContent(),requestParam.getValueType()));
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


    private HttpRequestBase wrapHttpRequest(String apiId, Map<String,Object> requestMap){
        ApiInfoModel apiInfoModel = infoFacade.getApiInfoModel(apiId);

        List<RequestParam> requestParams = apiInfoModel.getRequestParams();
        requestParams.forEach(requestParam -> {
            if (requestMap.containsKey(requestParam.getName())){
                requestParam.setValueContent(requestMap.get(requestParam.getName()).toString());
            }
        });

        return wrapHttpRequest(apiId,apiInfoModel);
    }

    @Override
    public String run(String apiId, ApiInfoModel model) {
        HttpRequestBase remoteRequest = wrapHttpRequest(apiId, model);
        // write response
        String responseContent = remoteCall(remoteRequest);
        responseContent = convert(responseContent,apiId);
        return responseContent;
    }



    @Override
    public String run(String apiId, HashMap<String, Object> map) {
        HttpRequestBase remoteRequest = wrapHttpRequest(apiId, map);
        // write response
        String responseContent = remoteCall(remoteRequest);
        responseContent = convert(responseContent,apiId);
        return responseContent;
    }

    @Override
    public ApiResult runWeb(String apiId, ApiInfoModel model) {
        ApiResult apiResult = new ApiResult();
        HttpRequestBase remoteRequest = wrapHttpRequest(apiId, model);
        remoteCall(remoteRequest,apiResult);
        if (200 != apiResult.getCode() ){
            return apiResult;
        }
        apiResult.setOriginalContent(apiResult.getContent());
        apiResult.setContent(convert(apiResult.getContent(),apiId));
        return apiResult;
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

    private void remoteCall(HttpRequestBase remoteRequest,ApiResult apiResult){
        if (apiResult == null){
            apiResult = new ApiResult();
        }
        String responseContent = null;
        CloseableHttpClient httpClient = null;
        try{
            org.apache.http.client.config.RequestConfig requestConfig = org.apache.http.client.config.RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            remoteRequest.setConfig(requestConfig);

            httpClient = HttpClients.custom().disableAutomaticRetries().build();

            Header[] requestAllHeaders = remoteRequest.getAllHeaders();
            for (Header header : requestAllHeaders) {
                apiResult.getRequestHeaders().put(header.getName(),header.getValue());
            }

            // parse response
            long start = new Monment().getTime();
            HttpResponse response = httpClient.execute(remoteRequest);
            long end = new Monment().getTime();
            apiResult.setTime(end-start);
            Header[] allHeaders = response.getAllHeaders();
            for (Header header : allHeaders) {
                apiResult.getResponseHeaders().put(header.getName(),header.getValue());
            }

            HttpEntity entity = response.getEntity();
            if (null != entity) {
                int statusCode = response.getStatusLine().getStatusCode();
                apiResult.setCode(statusCode);
                if (statusCode == 200) {
                    responseContent = EntityUtils.toString(entity, "UTF-8");
                    apiResult.setSize(Long.valueOf(responseContent.getBytes(StandardCharsets.UTF_8).length));
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

        apiResult.setContent(responseContent);
    }

    public String convert(String responseContent, String apiId) {
        List<FieldMapping> fieldMappings = Optional.ofNullable(fieldMappingRepository.many("apiId", apiId)).orElse(null);
        if (CollectionUtils.isEmpty(fieldMappings)){
            return responseContent;
        }
        Map<String,Object> map = new HashMap<>();
        fieldMappings.stream()
                .forEach(fieldMapping -> {
                    Object value = JsonPathUtils.readjson(responseContent,fieldMapping.getParamPath());
                    Object transform = transform(value, fieldMapping.getValueType());
                    map.put(fieldMapping.getName(),transform);
                });
        return JsonPathUtils.warpJson(map);

    }

    public Object transform(Object input, DataType dataType){
        if (null == input){
            return null;
        }
        if (dataType == null){
            return input;
        }
        switch (dataType){
            case String:
                return input;
            case Long:
                return Long.valueOf(input.toString());
            case Float:
                return Float.valueOf(input.toString());
            case Boolean:
                return Boolean.valueOf(input.toString());
            case Integer:
                return Integer.valueOf(input.toString());
            case Timestamp:
                return new Timestamp(new Monment(input.toString(), "yyyy-MM-dd HH:mm:ss").getTime());
        }
        return input;
    }
}
