package com.egova.api.condition;

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
@Display("")
public class RequestParamCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("参数名")
    @ConditionOperator(name = "name", operator = ClauseOperator.Equal)
    private String name;

    @Display("参数值")
    @ConditionOperator(name = "value", operator = ClauseOperator.Equal)
    private String value;

    @Display("参考值")
    @ConditionOperator(name = "example", operator = ClauseOperator.Equal)
    private String example;

    @Display("参数说明")
    @ConditionOperator(name = "description", operator = ClauseOperator.Equal)
    private String description;

    @Display("apiId")
    @ConditionOperator(name = "apiId", operator = ClauseOperator.Equal)
    private String apiId;

    @Display("参数类型【req_query】【req_params】【form-data】")
    @ConditionOperator(name = "type", operator = ClauseOperator.Equal)
    private String type;

}
