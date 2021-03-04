package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum ScriptType implements PropertyDescriptor {
    Groovy("0", "groovy"),
    Formula("1", "formula"),
    ;

    private final String value;
    private final String text;

}