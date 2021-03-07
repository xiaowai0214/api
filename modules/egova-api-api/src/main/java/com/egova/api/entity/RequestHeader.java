package com.egova.api.entity;

import com.egova.api.enums.RequestScope;
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
@Table(name = "api_request_header")
@Display("api头信息")
@EqualsAndHashCode(callSuper = true)
public class RequestHeader extends BaseEntity {

    public static final String NAME = "RequestHeader";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("名称")
    @Column(name = "name")
    private String name;

    @Display("值")
    @Column(name = "value")
    private String value;

    @Display("必填")
    @Column(name = "required")
    private boolean required;

    @Display("【apiId】【projectId】")
    @Column(name = "belongId")
    private String belongId;

    @Display("范围【全局 global, 适用于定义全局环境的头信息】，【单个api】")
    @Column(name = "scope")
    private RequestScope scope;

}
