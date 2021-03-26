package com.egova.api.authorization;

import com.egova.api.enums.AuthenticationType;

import java.util.Map;

public interface AuthenticationSupplier {

    AuthenticationType getType();

    Boolean match(AuthenticationType type);

    String supply(String url, Map<String, Object> data);


    String supply(String apiId);
}
