package com.egova.api.authorization.impl;

import com.egova.api.entity.Authentication;
import com.egova.api.enums.AuthenticationType;
import com.egova.api.model.AuthenticationBasicConfig;
import com.egova.json.utils.JsonUtils;
import com.flagwind.commons.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class BasicAuthSupplier extends AbstractAuthenticationSupplier {
    @Override
    public AuthenticationType getType() {
        return AuthenticationType.Base_Auth;
    }

    @Override
    public Boolean match(AuthenticationType type) {
        return getType() == type;
    }

    @Override
    public String supply(String url,Map<String, Object> data) {
        return null;
    }

    @Override
    public String supply(String apiId) {
        Authentication config = super.getConfig(apiId);
        String content = config.getContent();
        if (StringUtils.isBlank(content)){
            return null;
        }
        AuthenticationBasicConfig basicConfig = JsonUtils.deserialize(content, AuthenticationBasicConfig.class);
        String base64 = Base64Util.encode(basicConfig.getUsername() + ":" + basicConfig.getPassword());
        return "basic " + base64;
    }
}
