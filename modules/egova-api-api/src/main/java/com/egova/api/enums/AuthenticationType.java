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
    Api_Key("0", "Api Key"),
    Base_Auth("1", "Base Auth"),
    OAuth2("2", "OAuth2"),
    ;

    private final String value;
    private final String text;

}