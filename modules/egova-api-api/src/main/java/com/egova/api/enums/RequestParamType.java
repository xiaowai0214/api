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
    QueryString("0", "QueryString"),
    FormData("1", "FormData"),
    Json("2", "Json"),
    Path("3", "Path"),
    ;

    private final String value;
    private final String text;

}