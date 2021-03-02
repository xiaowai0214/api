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
@Table(name = "api_authentication")
@Display("api鉴权")
@EqualsAndHashCode(callSuper = true)
public class Authentication extends BaseEntity {

    public static final String NAME = "Authentication";

    @Id
    @Display("主键")
    @Column(name = "id")
    private String id;

    @Display("apiId")
    @Column(name = "apiId")
    private String apiId;

    @Display("认证方式，【api Key】【Base Auth】【OAuth2.0】")
    @Column(name = "type")
    private String type;

    @Display("参数")
    @Column(name = "param")
    private String param;

    @Display("认证地址")
    @Column(name = "url")
    private String url;

}
