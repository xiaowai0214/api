package com.egova.api.entity;

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
@Table(name = "api_header")
@Display("api头信息")
@EqualsAndHashCode(callSuper = true)
public class Header extends BaseEntity {

    public static final String NAME = "Header";

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
    private Integer required;

    @Display("【apiId】【environmentId】")
    @Column(name = "belongId")
    private String belongId;

    @Display("范围【全局 global, 适用于定义全局环境的头信息】，【单个api】")
    @Column(name = "scope")
    private String scope;

}
