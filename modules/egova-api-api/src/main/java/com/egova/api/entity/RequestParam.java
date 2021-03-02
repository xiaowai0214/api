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
@Table(name = "api_request_param")
@Display("")
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

    @Display("参数值")
    @Column(name = "value")
    private String value;

    @Display("参考值")
    @Column(name = "example")
    private String example;

    @Display("参数说明")
    @Column(name = "description")
    private String description;

    @Display("apiId")
    @Column(name = "apiId")
    private String apiId;

    @Display("参数类型【req_query】【req_params】【form-data】")
    @Column(name = "type")
    private String type;

}
