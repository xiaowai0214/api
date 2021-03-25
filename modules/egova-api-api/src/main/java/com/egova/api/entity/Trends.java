package com.egova.api.entity;

import com.egova.api.associative.ProjectNameProvider;
import com.egova.api.enums.OperateType;
import com.egova.api.enums.TrendsType;
import com.egova.associative.Associative;
import com.egova.associative.CategoryNameProvider;
import com.egova.model.BaseEntity;
import com.egova.model.annotation.Display;
import com.egova.security.UserContext;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
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
@Table(name = "api_trends")
@Display("api动态")
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class Trends extends BaseEntity {

    public static final String NAME = "Trends";

    @Id
    @Display("")
    @Column(name = "id")
    private String id;

    @Display("项目id")
    @Column(name = "projectId")
    @Associative(name = "projectName",providerClass = ProjectNameProvider.class)
    private String projectId;

    @Display("api分组")
    @Column(name = "categoryId")
    @Associative(name = "categoryName",providerClass = CategoryNameProvider.class)
    private String categoryId;

    @Display("apiId")
    @Column(name = "apiId")
    private String apiId;

    @Display("创建人")
    @Column(name = "creator")
    private String creator;

    @Display("创建时间")
    @Column(name = "createTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

//    @Display("项目名称")
//    @Column(name = "projectName")
//    private String projectName;

    @Display("api名称")
    @Column(name = "apiName")
    private String apiName;

    @Display("类型")
    @Column(name = "type")
    private TrendsType type;

    @Display("操作类型")
    @Column(name = "operateType")
    private OperateType operateType;

    public Trends(String projectId, String categoryId, String apiId, String apiName,TrendsType type, OperateType operateType) {
        this.projectId = projectId;
        this.categoryId = categoryId;
        this.apiId = apiId;
        this.apiName = apiName;
        this.type = type;
        this.operateType = operateType;
        this.creator = UserContext.username();
        this.createTime = new Timestamp(System.currentTimeMillis());
    }
}
