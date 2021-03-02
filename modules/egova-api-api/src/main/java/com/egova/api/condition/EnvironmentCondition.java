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
public class EnvironmentCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("环境名")
    @ConditionOperator(name = "name", operator = ClauseOperator.Equal)
    private String name;

    @Display("环境地址")
    @ConditionOperator(name = "domin", operator = ClauseOperator.Equal)
    private String domin;

}
