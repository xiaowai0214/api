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
@Table(name = "api_environment_param")
@Display("")
@EqualsAndHashCode(callSuper = true)
public class EnvironmentParam extends BaseEntity {

    public static final String NAME = "EnvironmentParam";

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

    @Display("备注")
    @Column(name = "remark")
    private String remark;

    @Display("环境id")
    @Column(name = "environmentId")
    private Integer environmentId;

}
