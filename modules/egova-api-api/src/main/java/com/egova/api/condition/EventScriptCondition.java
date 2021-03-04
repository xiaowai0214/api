package com.egova.api.condition;

import com.egova.api.enums.EventType;
import com.egova.api.enums.ScriptType;
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
@Display("事件脚本")
public class EventScriptCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("项目主键")
    @ConditionOperator(name = "projectId", operator = ClauseOperator.Equal)
    private String projectId;

    @Display("api主键")
    @ConditionOperator(name = "apiId", operator = ClauseOperator.Equal)
    private String apiId;

    @Display("事件类型[0:前置事件,1:后置事件]")
    @ConditionOperator(name = "eventType", operator = ClauseOperator.Equal)
    private EventType eventType;

    @Display("脚本类型0:groovy,1:formula]")
    @ConditionOperator(name = "type", operator = ClauseOperator.Equal)
    private ScriptType type;

    @Display("参数内容")
    @ConditionOperator(name = "content", operator = ClauseOperator.Equal)
    private String content;

    @Display("排序")
    @ConditionOperator(name = "sort", operator = ClauseOperator.Equal)
    private Integer sort;

}
