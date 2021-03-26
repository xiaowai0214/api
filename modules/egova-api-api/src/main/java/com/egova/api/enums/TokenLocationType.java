package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum TokenLocationType implements PropertyDescriptor {
    Request_Url("0", "Request_Url"),
    Request_Header("1", "Request_Header"),
    ;

    private final String value;
    private final String text;

}