package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum AuthenticationType implements PropertyDescriptor {
    Api_Key("0", "api_key"),
    Base_Auth("1", "base_auth"),
    OAuth2("1", "oauth2"),
    ;

    private final String value;
    private final String text;

}