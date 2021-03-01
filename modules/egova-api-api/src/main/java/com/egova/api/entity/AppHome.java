package com.egova.api.entity;

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
@Table(name = "frm_app_home")
@Display("App应用")
@EqualsAndHashCode(callSuper = true)
public class AppHome extends BaseEntity {

    public static final String NAME = "AppHome";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("名称")
    @Column(name = "name")
    private String name;

    @Display("页面结构")
    @Column(name = "content")
    private String content;

    @Display("状态")
    @Column(name = "status")
    private Integer status;

    @Display("创建人")
    @Column(name = "creator")
    private String creator;

    @Display("创建时间")
    @Column(name = "createTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @Display("修改人")
    @Column(name = "modifier")
    private String modifier;

    @Display("修改时间")
    @Column(name = "modifyTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp modifyTime;

}
