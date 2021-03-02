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
@Display("api鉴权")
public class AuthenticationCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("apiId")
    @ConditionOperator(name = "apiId", operator = ClauseOperator.Equal)
    private String apiId;

    @Display("认证方式，【api Key】【Base Auth】【OAuth2.0】")
    @ConditionOperator(name = "type", operator = ClauseOperator.Equal)
    private String type;

    @Display("参数")
    @ConditionOperator(name = "param", operator = ClauseOperator.Equal)
    private String param;

    @Display("认证地址")
    @ConditionOperator(name = "url", operator = ClauseOperator.Equal)
    private String url;

}
