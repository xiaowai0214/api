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
@Display("")
public class ApiCondition implements Serializable {

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
    private String method;

    @Display("请求路径")
    @ConditionOperator(name = "url", operator = ClauseOperator.Equal)
    private String url;

    @Display("状态")
    @ConditionOperator(name = "status", operator = ClauseOperator.Equal)
    private String status;

    @Display("body参数类型【multipart/form-data 】【json】【】。。。")
    @ConditionOperator(name = "requestBodyType", operator = ClauseOperator.Equal)
    private String requestBodyType;

    @Display("json类型的body参数")
    @ConditionOperator(name = "requestBodyJson", operator = ClauseOperator.Equal)
    private String requestBodyJson;

    @Display("文档id")
    @ConditionOperator(name = "documentId", operator = ClauseOperator.Equal)
    private String documentId;

    @Display("响应结果")
    @ConditionOperator(name = "response", operator = ClauseOperator.Equal)
    private String response;

    @Display("成功样例")
    @ConditionOperator(name = "successExample", operator = ClauseOperator.Equal)
    private String successExample;

    @Display("失败样例")
    @ConditionOperator(name = "failExample", operator = ClauseOperator.Equal)
    private String failExample;

    @Display("创建人")
    @ConditionOperator(name = "creator", operator = ClauseOperator.Equal)
    private String creator;

    @Display("创建时间")
    @ConditionOperator(name = "createTime", operator = ClauseOperator.Equal)
    private Timestamp createTime;

    @Display("备注")
    @ConditionOperator(name = "remark", operator = ClauseOperator.Equal)
    private String remark;

    @Display("前置脚本")
    @ConditionOperator(name = "preScript", operator = ClauseOperator.Equal)
    private String preScript;

    @Display("后置脚本")
    @ConditionOperator(name = "postScript", operator = ClauseOperator.Equal)
    private String postScript;

    @Display("mock数据")
    @ConditionOperator(name = "mock", operator = ClauseOperator.Equal)
    private String mock;

}
