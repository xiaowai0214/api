package com.egova.api.entity.codes;

import com.egova.api.entity.Project;
import com.egova.api.facade.ProjectFacade;
import com.egova.associative.Associative;
import com.egova.associative.CodeTypeProvider;
import com.egova.lang.DataIntro;
import com.flagwind.application.Application;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author huangkang
 * @description: 分类字段
 * @date 2020-04-21 15:43:46
 */
@Associative(name = "_${name}", providerClass = CodeTypeProvider.class)
@Slf4j
public class ProjectIntro extends DataIntro {


    private String value;

    public ProjectIntro(String value) {
        this.value = value;
    }

    public static ProjectIntro of(String value) {
        return new ProjectIntro(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        ProjectFacade facade = Application.resolve(ProjectFacade.class);
        // guava的cache来进行内存模式缓层数据
        Map<String, String> nameMap = synchronizeLoad(ProjectIntro.class, Project.NAME + ":name-map", () -> facade.getNameMap());
        return nameMap.get(this.value);

    }

    public void invalid() {
        invalidate(Project.NAME + ":name-map");
    }

}
