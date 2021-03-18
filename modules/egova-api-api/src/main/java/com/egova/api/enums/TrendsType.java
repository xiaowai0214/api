package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum TrendsType implements PropertyDescriptor {
    Project("0", "项目"),
    APi_Category("1", "api分组"),
    APi("2", "api"),
    ;

    private final String value;
    private final String text;

}