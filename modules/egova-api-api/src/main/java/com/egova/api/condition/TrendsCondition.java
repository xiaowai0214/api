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
@Display("api动态")
public class TrendsCondition implements Serializable {

    @Display("")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("项目id")
    @ConditionOperator(name = "projectId", operator = ClauseOperator.Equal)
    private String projectId;

    @Display("api分组")
    @ConditionOperator(name = "categoryId", operator = ClauseOperator.Equal)
    private String categoryId;

    @Display("apiId")
    @ConditionOperator(name = "apiId", operator = ClauseOperator.Equal)
    private String apiId;

    @Display("创建人")
    @ConditionOperator(name = "creator", operator = ClauseOperator.Equal)
    private String creator;

    @Display("创建时间")
    @ConditionOperator(name = "createTime", operator = ClauseOperator.GreaterThanEqual)
    private Timestamp startCreateTime;

    @Display("创建时间")
    @ConditionOperator(name = "createTime", operator = ClauseOperator.LessThanEqual)
    private Timestamp endCreateTime;

    @Display("api名称")
    @ConditionOperator(name = "apiName", operator = ClauseOperator.Like)
    private String apiName;

}
