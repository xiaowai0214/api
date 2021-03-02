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
@Table(name = "api_project")
@Display("api项目表")
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {

    public static final String NAME = "Project";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("名称")
    @Column(name = "name")
    private String name;

    @Display("")
    @Column(name = "description")
    private String description;

    @Display("类别id")
    @Column(name = "categoryId")
    private String categoryId;

    @Display("排序")
    @Column(name = "sort")
    private Integer sort;

    @Display("创建人")
    @Column(name = "creator")
    private String creator;

    @Display("创建时间")
    @Column(name = "createTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

}
