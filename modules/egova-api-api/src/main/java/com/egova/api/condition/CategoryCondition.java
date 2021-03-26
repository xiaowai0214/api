package com.egova.api.condition;

import com.egova.model.annotation.Display;
import com.flagwind.persistent.annotation.Condition;
import com.flagwind.persistent.annotation.ConditionOperator;
import com.flagwind.persistent.model.ClauseOperator;
import lombok.Data;

@Display("模块功能")
@Condition
@Data
public class CategoryCondition {

    @Display("主键")
    @ConditionOperator(name = "Id", operator = ClauseOperator.Equal)
    private String id;

    @Display("名称")
    @ConditionOperator(name = "Name", operator = ClauseOperator.Equal)
    private String name;

    @Display("项目id")
    @ConditionOperator(name = "projectId", operator = ClauseOperator.Equal)
    private String projectId;

    @Display("父类id")
    @ConditionOperator(name = "parentId", operator = ClauseOperator.Equal)
    private String parentId;

    @Display("类型")
    @ConditionOperator(name = "type", operator = ClauseOperator.Equal)
    private String type;

    @Display("排序")
    @ConditionOperator(name = "sort", operator = ClauseOperator.GreaterThanEqual)
    private Integer startSort;

    @Display("排序")
    @ConditionOperator(name = "sort", operator = ClauseOperator.LessThanEqual)
    private Integer endSort;


}
