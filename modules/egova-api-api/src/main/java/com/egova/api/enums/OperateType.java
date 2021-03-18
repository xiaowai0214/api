package com.egova.api.enums;

import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.model.PropertyDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Getter
@RequiredArgsConstructor
public enum OperateType implements PropertyDescriptor {
    Insert("0", "新增"),
    Update("1", "更新"),
    Delete("2", "删除"),
    ;

    private final String value;
    private final String text;

}