package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum DataType implements PropertyDescriptor {
    Integer("0", "Integer"),
    Long("1", "Long"),
    String("2", "String"),
    Float("3", "Float"),
    Boolean("4", "Boolean"),
    Timestamp("5", "Timestamp"),
    ;

    private final String value;
    private final String text;

}