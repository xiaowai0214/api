package com.egova.api.entity;

import com.egova.api.enums.RequestMethodType;
import com.egova.model.BaseEntity;
import com.egova.model.annotation.Display;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * created by huangkang
 */
@Data
@Entity
@Table(name = "api_api")
@Display("")
@EqualsAndHashCode(callSuper = true)
public class Api extends BaseEntity {

    public static final String NAME = "Api";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("名称")
    @Column(name = "name")
    private String name;

    @Display("api项目id")
    @Column(name = "projectId")
    private String projectId;

    @Display("api分组id")
    @Column(name = "categoryId")
    private String categoryId;

    @Display("请求方式")
    @Column(name = "method")
    private RequestMethodType method;

    @Display("请求路径")
    @Column(name = "url")
    private String url;

    @Display("状态")
    @Column(name = "status")
    private String status;

    @Display("body参数类型【multipart/form-data 】【json】【】。。。")
    @Column(name = "requestBodyType")
    private String requestBodyType;

    @Display("json类型的body参数")
    @Column(name = "requestBodyJson")
    private String requestBodyJson;

    @Display("文档id")
    @Column(name = "documentId")
    private String documentId;

    @Display("响应结果")
    @Column(name = "response")
    private String response;

    @Display("成功样例")
    @Column(name = "successExample")
    private String successExample;

    @Display("失败样例")
    @Column(name = "failExample")
    private String failExample;

    @Display("创建人")
    @Column(name = "creator")
    private String creator;

    @Display("创建时间")
    @Column(name = "createTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @Display("备注")
    @Column(name = "remark")
    private String remark;

    @Display("前置脚本")
    @Column(name = "preScript")
    private String preScript;

    @Display("后置脚本")
    @Column(name = "postScript")
    private String postScript;

    @Display("mock数据")
    @Column(name = "mock")
    private String mock;

}