package com.egova.api.service;

import com.egova.api.entity.Category;
import com.egova.api.facade.ApiCategoryFacade;
import com.egova.json.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 基础微服务包装
 */
@Service
@RequiredArgsConstructor
public class BaseMicroServiceWrapper {

    private final ApiCategoryFacade categoryFacade;

    public List<Category> getCategoryTreeById(String id, List<String> idList) {
        List<Category> tree = categoryFacade.getTreeById(id);
        if (CollectionUtils.isEmpty(tree)) {
            return Collections.emptyList();
        }
        tree.forEach(g->setCategoryChildren(g,idList));
        return tree;
    }

    public List<Category> getCategoryTreeByTypeAndProjectId(String type,String projectId,List<String> idList) {
        List<Category> tree = categoryFacade.getTreeByTypeAndProject(type,projectId);
        if (CollectionUtils.isEmpty(tree)) {
            return Collections.emptyList();
        }
        tree.forEach(g->setCategoryChildren(g,idList));
        return tree;
    }

    public <T> List getCategoryTreeById(String id, String nodeName, Map<String, List<T>> groupTables) {

        List<Category> tree = categoryFacade.getTreeById(id);
        if (CollectionUtils.isEmpty(tree)) {
            return Collections.emptyList();
        }

        tree.forEach(category -> parseTree(nodeName, category, groupTables));
        return tree;
    }

    public <T> List getCategoryTreeByType(String type, String nodeName, Map<String, List<T>> groupTables) {
        List<Category> tree = categoryFacade.getTreeByType(type);
        if (CollectionUtils.isEmpty(tree)) {
            return Collections.emptyList();
        }

        tree.forEach(category -> parseTree(nodeName, category, groupTables));
        return tree;
    }

    public <T> void setCategoryNodeData(String nodeName, Category category, Map<String, List<T>> groupTables) {
        List<T> tableList = groupTables.getOrDefault(category.getId(), null);
        category.set(nodeName, tableList);
        List<Category> children = (List<Category>) category.get("children");
        Optional.ofNullable(children).ifPresent(list -> list.forEach(g -> setCategoryNodeData(nodeName, g, groupTables)));
    }


    private <T> void parseTree(String nodeName, Category category, Map<String, List<T>> groupTables) {
        List<T> tableList = groupTables.getOrDefault(category.getId(), null);
        category.set(nodeName, tableList);
        Object children = category.get("children");
        if (children == null) {
            return;
        }
        List<Category> list = JsonUtils.deserializeList(JsonUtils.serialize(children), Category.class);
        category.set("children", list);
        for (Category cate : list) {
            parseTree(nodeName, cate, groupTables);
        }
    }


    private <T> void setCategoryChildren(Category category,List<String> ids) {
        Object children = category.get("children");
        if (children == null) {
            return;
        }
        List<Category> list = JsonUtils.deserializeList(JsonUtils.serialize(children), Category.class);
        category.set("children", list);
        ids.add(category.getId());
        ids.addAll(list.stream().map(Category::getId).collect(Collectors.toList()));
        for (Category cate : list) {
            setCategoryChildren(cate, ids);
        }
    }
}
