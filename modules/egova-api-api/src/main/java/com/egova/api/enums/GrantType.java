package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum GrantType implements PropertyDescriptor {
    password("0", "password"),
    client("1", "client_credentials"),
    ;

    private final String value;
    private final String text;

}