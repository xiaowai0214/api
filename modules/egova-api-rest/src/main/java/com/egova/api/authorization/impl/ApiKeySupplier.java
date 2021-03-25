package com.egova.api.authorization.impl;

import com.egova.api.authorization.AuthenticationSupplier;
import com.egova.api.enums.AuthenticationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ApiKeySupplier implements AuthenticationSupplier {

    @Override
    public AuthenticationType getType() {
        return AuthenticationType.Api_Key;
    }

    @Override
    public Boolean match(AuthenticationType type) {
        return getType() == type;
    }

    @Override
    public String supply(String url, Map<String, Object> data) {
        return null;
    }
}
