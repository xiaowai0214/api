package com.egova.api.authorization.impl;

import com.egova.api.domain.AuthenticationRepository;
import com.egova.api.domain.InfoRepository;
import com.egova.api.entity.Authentication;
import com.egova.api.entity.Info;
import com.egova.api.entity.RequestHeader;
import com.egova.api.enums.AuthenticationType;
import com.egova.api.enums.GrantType;
import com.egova.api.enums.RequestBodyType;
import com.egova.api.enums.RequestMethodType;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.model.AuthenticationOauth2Config;
import com.egova.api.util.HttpUtils;
import com.egova.api.util.JsonPathUtils;
import com.egova.json.utils.JsonUtils;
import com.flagwind.commons.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2Supplier extends AbstractAuthenticationSupplier {

    private final InfoRepository infoRepository;
    private final AuthenticationRepository authenticationRepository;

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
        ApiInfoModel apiInfoModel = initApiModel(url,data);
        String s = HttpUtils.remoteCall(apiInfoModel);
        return s;
    }

    @Override
    public String supply(String apiId) {
        Authentication authentication = super.getConfig(apiId);
        String content = authentication.getContent();
        if (StringUtils.isBlank(content)){
            return null;
        }
        AuthenticationOauth2Config oauth2Config = JsonUtils.deserialize(content, AuthenticationOauth2Config.class);
        Map<String,Object> parmas = wrapParams(oauth2Config);
        String supply = supply(oauth2Config.getTokenUrl(), parmas);
        Object token = JsonPathUtils.readjson(supply, oauth2Config.getTokenPath());
        return StringUtils.isBlank(oauth2Config.getHeaderPrefix()) ? token.toString() : oauth2Config.getHeaderPrefix() + " " + token.toString();
    }

    private Map<String, Object> wrapParams(AuthenticationOauth2Config oauth2Config) {
        Map<String, Object> map = new HashMap<>();
        if (oauth2Config.getGrantType() == null){
            return map;
        }
        switch (oauth2Config.getGrantType()){
            case client:
                map.put("grant_type", GrantType.client.getText());
                map.put("client_id", oauth2Config.getClientId());
                map.put("client_secret", oauth2Config.getClientSecret());
                break;
            case password:
                map.put("grant_type", GrantType.password.getText());
                map.put("client_id", oauth2Config.getClientId());
                map.put("client_secret", oauth2Config.getClientSecret());
                map.put("username", oauth2Config.getUsername());
                map.put("password", oauth2Config.getPassword());
                break;
            default:
                break;
        }
        return map;
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
