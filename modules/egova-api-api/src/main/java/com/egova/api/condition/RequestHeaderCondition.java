package com.egova.api.condition;

import com.egova.api.enums.RequestScope;
import com.egova.model.annotation.Display;
import com.flagwind.persistent.annotation.Condition;
import com.flagwind.persistent.annotation.ConditionOperator;
import com.flagwind.persistent.model.ClauseOperator;
import lombok.Data;

import java.io.Serializable;

/**
 * created by huangkang
 */
@Data
@Condition
@Display("api头信息")
public class RequestHeaderCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("名称")
    @ConditionOperator(name = "name", operator = ClauseOperator.Equal)
    private String name;

    @Display("值")
    @ConditionOperator(name = "value", operator = ClauseOperator.Equal)
    private String value;

    @Display("必填")
    @ConditionOperator(name = "required", operator = ClauseOperator.Equal)
    private Integer required;

    @Display("【apiId】【projectId】")
    @ConditionOperator(name = "belongId", operator = ClauseOperator.Equal)
    private String belongId;

    @Display("范围【全局 global, 适用于定义全局环境的头信息】，【单个api】")
    @ConditionOperator(name = "scope", operator = ClauseOperator.Equal)
    private RequestScope scope;

}
