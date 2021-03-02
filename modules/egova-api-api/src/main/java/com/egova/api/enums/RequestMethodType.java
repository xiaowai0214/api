package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum RequestMethodType implements PropertyDescriptor {
    GET("0", "GET"),
    POST("1", "POST"),
    PUT("2", "PUT"),
    DELETE("3", "DELETE"),
    ;

    private final String value;
    private final String text;

}