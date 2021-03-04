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
@Display("api项目表")
public class ProjectCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("名称")
    @ConditionOperator(name = "name", operator = ClauseOperator.Like)
    private String name;

    @Display("排序")
    @ConditionOperator(name = "sort", operator = ClauseOperator.Equal)
    private Integer sort;

    @Display("创建人")
    @ConditionOperator(name = "creator", operator = ClauseOperator.Equal)
    private String creator;

}
