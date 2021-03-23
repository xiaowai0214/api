package com.egova.api.entity;

import com.egova.api.enums.DataType;
import com.egova.api.enums.RequestParamType;
import com.egova.model.BaseEntity;
import com.egova.model.annotation.Display;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by huangkang
 */
@Data
@Entity
@Table(name = "api_request_param")
@Display("api请求参数")
@EqualsAndHashCode(callSuper = true)
public class RequestParam extends BaseEntity {

    public static final String NAME = "RequestParam";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("参数名")
    @Column(name = "name")
    private String name;

    @Display("参数说明")
    @Column(name = "text")
    private String text;

    @Display("参考值")
    @Column(name = "example")
    private String example;

    @Display("apiId")
    @Column(name = "apiId")
    private String apiId;

    @Display("参数类型【QueryString】【FormData】【Json】")
    @Column(name = "type")
    private RequestParamType type;

    @Display("值类型")
    @Column(name = "valueType")
    private DataType valueType;

    @Display("值内容")
    @Column(name = "valueContent")
    private String valueContent;

    @Display("是否禁用")
    @Column(name = "disabled")
    private boolean disabled;

}
