package com.egova.api.entity.codes;

import com.egova.api.facade.ApiCategoryFacade;
import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.entity.Category;
import com.egova.lang.DataIntro;
import com.flagwind.application.Application;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author chendb
 * @description: 分类字段
 * @date 2020-04-21 15:43:46
 */
@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Slf4j
public class CategoryIntro extends DataIntro {


    private String value;

    public CategoryIntro(String value) {
        this.value = value;
    }

    public static CategoryIntro of(String value) {
        return new CategoryIntro(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        ApiCategoryFacade facade = Application.resolve(ApiCategoryFacade.class);
        // guava的cache来进行内存模式缓层数据
        Map<String, String> nameMap = synchronizeLoad(CategoryIntro.class, Category.NAME + ":name-map", () -> facade.getNameMap());
        return nameMap.get(this.value);

    }
}
