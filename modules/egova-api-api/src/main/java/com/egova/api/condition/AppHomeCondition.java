package com.egova.api.condition;

import com.egova.model.annotation.Display;
import com.flagwind.persistent.annotation.Condition;
import com.flagwind.persistent.annotation.ConditionOperator;
import com.flagwind.persistent.model.ClauseOperator;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * created by huangkang
 */
@Data
@Condition
@Display("App应用")
public class AppHomeCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("名称")
    @ConditionOperator(name = "name", operator = ClauseOperator.Like)
    private String name;

    @Display("状态")
    @ConditionOperator(name = "status", operator = ClauseOperator.Equal)
    private Integer status;

    @Display("创建人")
    @ConditionOperator(name = "creator", operator = ClauseOperator.Equal)
    private String creator;

    @Display("创建时间")
    @ConditionOperator(name = "createTime", operator = ClauseOperator.GreaterThanEqual)
    private Timestamp createTimeStart;

    @Display("创建时间")
    @ConditionOperator(name = "createTime", operator = ClauseOperator.LessThanEqual)
    private Timestamp createTimeEnd;



}
