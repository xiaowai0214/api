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
public class EnvironmentParamCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("名称")
    @ConditionOperator(name = "name", operator = ClauseOperator.Equal)
    private String name;

    @Display("值")
    @ConditionOperator(name = "value", operator = ClauseOperator.Equal)
    private String value;

    @Display("备注")
    @ConditionOperator(name = "remark", operator = ClauseOperator.Equal)
    private String remark;

    @Display("环境id")
    @ConditionOperator(name = "environmentId", operator = ClauseOperator.Equal)
    private Integer environmentId;

}
