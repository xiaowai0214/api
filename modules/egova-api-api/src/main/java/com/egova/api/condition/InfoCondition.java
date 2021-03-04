package com.egova.api.condition;

import com.egova.api.enums.RequestBodyType;
import com.egova.api.enums.RequestMethodType;
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
@Display("api信息")
public class InfoCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("名称")
    @ConditionOperator(name = "name", operator = ClauseOperator.Equal)
    private String name;

    @Display("api项目id")
    @ConditionOperator(name = "projectId", operator = ClauseOperator.Equal)
    private String projectId;

    @Display("api分组id")
    @ConditionOperator(name = "categoryId", operator = ClauseOperator.Equal)
    private String categoryId;

    @Display("请求方式")
    @ConditionOperator(name = "method", operator = ClauseOperator.Equal)
    private RequestMethodType method;

    @Display("请求路径")
    @ConditionOperator(name = "url", operator = ClauseOperator.Like)
    private String url;

    @Display("状态")
    @ConditionOperator(name = "status", operator = ClauseOperator.Equal)
    private String status;

    @Display("body参数类型【multipart/form-data 】【json】【】。。。")
    @ConditionOperator(name = "requestBodyType", operator = ClauseOperator.Equal)
    private RequestBodyType requestBodyType;

    @Display("文档id")
    @ConditionOperator(name = "documentId", operator = ClauseOperator.Equal)
    private String documentId;

    @Display("创建人")
    @ConditionOperator(name = "creator", operator = ClauseOperator.Equal)
    private String creator;

    @Display("创建时间")
    @ConditionOperator(name = "createTime", operator = ClauseOperator.GreaterThanEqual)
    private Timestamp startCreateTime;

    @Display("创建时间")
    @ConditionOperator(name = "createTime", operator = ClauseOperator.LessThanEqual)
    private Timestamp endCreateTime;

    @Display("认证")
    @ConditionOperator(name = "authenticationId", operator = ClauseOperator.Equal)
    private String authenticationId;

}
