package com.egova.api.entity;

import com.egova.api.enums.AuthenticationType;
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
@Table(name = "api_authentication")
@Display("api鉴权")
@EqualsAndHashCode(callSuper = true)
public class Authentication extends BaseEntity {

    public static final String NAME = "Authentication";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("projectId")
    @Column(name = "projectId")
    private String projectId;

    @Display("认证方式，【api Key】【Base Auth】【OAuth2.0】")
    @Column(name = "type")
    private AuthenticationType type;

    @Display("参数内容")
    @Column(name = "content")
    private String content;

    @Display("存放位置，【Header】【queryString】")
    @Column(name = "location")
    private String location;

    @Display("存放token的key")
    @Column(name = "locationKey")
    private String locationKey;


}
