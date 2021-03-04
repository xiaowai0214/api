package com.egova.api.entity;

import com.egova.api.enums.EventType;
import com.egova.api.enums.ScriptType;
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
@Table(name = "api_event_script")
@Display("事件脚本")
@EqualsAndHashCode(callSuper = true)
public class EventScript extends BaseEntity {

    public static final String NAME = "EventScript";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("项目主键")
    @Column(name = "projectId")
    private String projectId;

    @Display("api主键")
    @Column(name = "apiId")
    private String apiId;

    @Display("事件类型[0:前置事件,1:后置事件]")
    @Column(name = "eventType")
    private EventType eventType;

    @Display("脚本类型0:groovy,1:formula]")
    @Column(name = "type")
    private ScriptType type;

    @Display("参数内容")
    @Column(name = "content")
    private String content;

    @Display("排序")
    @Column(name = "sort")
    private Integer sort;

}
