package com.egova.api.condition;

import com.egova.api.enums.AuthenticationType;
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
@Display("api鉴权")
public class AuthenticationCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("projectId")
    @ConditionOperator(name = "projectId", operator = ClauseOperator.Equal)
    private String projectId;

    @Display("认证方式，【api Key】【Base Auth】【OAuth2.0】")
    @ConditionOperator(name = "type", operator = ClauseOperator.Equal)
    private AuthenticationType type;

    @Display("参数内容")
    @ConditionOperator(name = "content", operator = ClauseOperator.Equal)
    private String content;

}
