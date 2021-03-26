package com.egova.api.authorization.impl;

import com.egova.api.enums.AuthenticationType;
import lombok.extern.slf4j.Slf4j;
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
        return null;
    }
}
