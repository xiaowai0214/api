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
@Table(name = "api_environment")
@Display("")
@EqualsAndHashCode(callSuper = true)
public class Environment extends BaseEntity {

    public static final String NAME = "Environment";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("环境名")
    @Column(name = "name")
    private String name;

    @Display("环境地址")
    @Column(name = "domin")
    private String domin;

}
