package com.egova.api.authorization.impl;

import com.egova.api.authorization.AuthenticationSupplier;
import com.egova.api.entity.Info;
import com.egova.api.entity.RequestHeader;
import com.egova.api.enums.AuthenticationType;
import com.egova.api.enums.RequestBodyType;
import com.egova.api.enums.RequestMethodType;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.util.HttpUtils;
import com.egova.json.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OAuth2Supplier implements AuthenticationSupplier {
    @Override
    public AuthenticationType getType() {
        return AuthenticationType.OAuth2;
    }

    @Override
    public Boolean match(AuthenticationType type) {
        return getType() == type;
    }

    @Override
    public String supply(String url, Map<String, Object> data) {
//        Object urlObj= Optional.ofNullable(data.get("url")).orElseThrow(()-> ExceptionUtils.api("认证url为配置"));
//        String url = urlObj.toString();
        ApiInfoModel apiInfoModel = initApiModel(url,data);
        String s = HttpUtils.remoteCall(apiInfoModel);
        return s;
    }

    private ApiInfoModel initApiModel(String url, Map<String, Object> data) {
        ApiInfoModel apiInfoModel = new ApiInfoModel();
        Info info = new Info();
        info.setUrl(url);
        info.setMethod(RequestMethodType.POST);
        info.setRequestBodyType(RequestBodyType.Json);
        apiInfoModel.setInfo(info);

        List<RequestHeader> requestHeaders = new ArrayList<>();
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setName("Content-Type");
        requestHeader.setValue("application/json");
        requestHeaders.add(requestHeader);
        apiInfoModel.setRequestHeaders(requestHeaders);

        apiInfoModel.setJson(JsonUtils.serialize(data));
        return apiInfoModel;
    }
}
