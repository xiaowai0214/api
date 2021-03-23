package com.egova.api.condition;

import com.egova.api.enums.RequestParamType;
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
@Display("api请求参数")
public class RequestParamCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("参数名")
    @ConditionOperator(name = "name", operator = ClauseOperator.Like)
    private String name;

    @Display("参数说明")
    @ConditionOperator(name = "text", operator = ClauseOperator.Like)
    private String text;

    @Display("参考值")
    @ConditionOperator(name = "example", operator = ClauseOperator.Like)
    private String example;

    @Display("apiId")
    @ConditionOperator(name = "apiId", operator = ClauseOperator.Equal)
    private String apiId;

    @Display("参数类型【QueryString】【FormData】【Json】")
    @ConditionOperator(name = "type", operator = ClauseOperator.Equal)
    private RequestParamType type;

    @Display("值类型")
    @ConditionOperator(name = "valueType", operator = ClauseOperator.Equal)
    private Integer valueType;

    @Display("是否启用")
    @ConditionOperator(name = "enabled", operator = ClauseOperator.Equal)
    private Boolean enabled;

}
