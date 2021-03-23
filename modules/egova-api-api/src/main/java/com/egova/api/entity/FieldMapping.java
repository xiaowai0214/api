package com.egova.api.entity;

import com.egova.api.enums.DataType;
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
@Table(name = "api_field_mapping")
@Display("api结果转换字段映像")
@EqualsAndHashCode(callSuper = true)
public class FieldMapping extends BaseEntity {

    public static final String NAME = "FieldMapping";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("")
    @Column(name = "apiId")
    private String apiId;

    @Display("原始参数路劲")
    @Column(name = "originalParamPath")
    private String originalParamPath;

    @Display("参数路劲")
    @Column(name = "paramPath")
    private String paramPath;

    @Display("新参数名称")
    @Column(name = "name")
    private String name;

    @Display("参数值")
    @Column(name = "valueContent")
    private String valueContent;

    @Display("值类型")
    @Column(name = "valueType")
    private DataType valueType;

}
