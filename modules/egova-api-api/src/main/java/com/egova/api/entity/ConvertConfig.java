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
 * created by 迷途小码农
 */
@Data
@Entity
@Table(name = "api_convert_config")
@Display("")
@EqualsAndHashCode(callSuper = true)
public class ConvertConfig extends BaseEntity {

    public static final String NAME = "ConvertConfig";

    @Id
    @Display("")
    @Column(name = "id")
    private String id;

    @Display("")
    @Column(name = "apiId")
    private String apiId;

    @Display("转换根节点")
    @Column(name = "convertRoot")
    private String convertRoot;

    @Display("接口成功调用状态吗路径")
    @Column(name = "successCodePath")
    private String successCodePath;

    @Display("接口调用成功标识")
    @Column(name = "successCode")
    private String successCode;

}
