package com.egova.api.entity;

import com.egova.api.associative.ApiCategoryNameProvider;
import com.egova.associative.Associative;
import com.egova.model.BaseEntity;
import com.egova.model.annotation.Display;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;


/**
 * @author huangkang
 * @Description: 通用树形分类表
 * @date 2020年02月20日 下午4:33:40
 */
@Entity
@Table(name = "api_category")
@Display("通用树形分类表")
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity implements Comparable<Category> {

    public static final String NAME = "base:category";

    @Display("主键")
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Display("名称")
    @Column(name = "name")
    private String name;

    @Display("项目id")
    @Column(name = "projectId")
    private String projectId;

    @Display("父级id")
    @Column(name = "parentId")
    @Associative(name = "parentName",providerClass = ApiCategoryNameProvider.class)
    private String parentId;

    @Display("排序")
    @Column(name = "sort")
    private Integer sort;

    @Display("分类类型")
    @Column(name = "type")
    private String type;

    @Display("禁用标识")
    @Column(name = "disabled")
    private boolean disabled;

    @Override
    public int compareTo(@NotNull Category o) {
        if (o.getSort() == null && this.getSort() == null) {
            return 0;
        }
        if (this.getSort() == null) {
            return -1;
        }
        if (o.getSort() == null) {
            return 1;
        }
        return this.getSort().compareTo(o.getSort());
    }

}
