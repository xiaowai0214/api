package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum RequestParamType implements PropertyDescriptor {
    Form("0", "multipart/form-data"),
    Path("1", "path"),
    Request("1", "request"),
    ;

    private final String value;
    private final String text;

}