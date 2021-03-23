package com.egova.api.condition;

import com.egova.model.annotation.Display;
import com.flagwind.persistent.annotation.Condition;
import com.flagwind.persistent.annotation.ConditionOperator;
import com.flagwind.persistent.model.ClauseOperator;
import lombok.Data;

import java.io.Serializable;

/**
 * created by 迷途小码农
 */
@Data
@Condition
@Display("")
public class ConvertConfigCondition implements Serializable {

    @Display("")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("")
    @ConditionOperator(name = "apiId", operator = ClauseOperator.Equal)
    private String apiId;

    @Display("转换根节点")
    @ConditionOperator(name = "convertRoot", operator = ClauseOperator.Equal)
    private String convertRoot;

    @Display("接口成功调用状态吗路径")
    @ConditionOperator(name = "successCodePath", operator = ClauseOperator.Equal)
    private String successCodePath;

    @Display("接口调用成功标识")
    @ConditionOperator(name = "sucessCode", operator = ClauseOperator.Equal)
    private String sucessCode;

}
