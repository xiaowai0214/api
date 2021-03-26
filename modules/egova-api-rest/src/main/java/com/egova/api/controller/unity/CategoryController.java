package com.egova.api.controller.unity;


import com.egova.api.condition.AdjustSortCondition;
import com.egova.api.entity.Category;
import com.egova.api.facade.ApiCategoryFacade;
import com.egova.api.service.CategoryService;
import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/unity/api/category"})
public class CategoryController implements ApiCategoryFacade {

    // region 私有变量


    private final CategoryService service;


    // endregion

    // region 请求动作

    @Api
    @Override
    public List<Category> getListByType(String type) {
        return service.getListByType(type);
    }

    /**
     * 判断租户名是否唯一
     *
     * @return OperateResult<Boolean> 返回类型
     */
    @Api
    @RequestMapping(value = "/conflict", method = RequestMethod.GET)
    public Boolean conflict(@RequestParam("name") String name) {
        return service.conflict(name);
    }

    /**
     * 删除单记录
     *
     * @param id 选择的记录id
     */
    @Api
    @Override
    public int deleteById(@PathVariable("id") String id) {
        return service.deleteById(id);
    }


    /**
     * 批量删除
     *
     * @return 返回类型
     */
    @Api
    @PostMapping(value = "/batch")
    @RequestDecorating("delete")
    public int deleteByIds(@RequestBody String[] key) {
        return service.deleteByIds(key);
    }

    /**
     * 调整顺序
     */
    @Api
    @PostMapping(value = "/adjust-sort")
    public boolean adjustSort(@RequestBody AdjustSortCondition condition) {
        return service.adjustSort(condition);
    }

    @Api
    @Override
    public void insertList(List<Category> categories) {
        service.insertList(categories);
    }

    /**
     * 批量更新
     */
    @Api
    @Override
    public void updateList(@RequestBody List<Category> categories) {
        service.updateList(categories);
    }

    /**
     * 获取实体
     */
    @Api
    @Override
    public Category getById(@PathVariable String id) {
        return service.getById(id);
    }

    @Api
    @Override
    public List<Category> getByIds(@RequestBody List<String> ids) {
        return service.getByIds(ids);
    }

    // endregion

    /**
     * 保存数据
     *
     * @return OperateResult<Boolean> 返回类型
     */
    @Api
    @Override
    public String insert(@RequestBody Category entity) {
        return service.insert(entity);
    }

    @Api
    @Override
    public void update(@RequestBody Category entity) {
        service.update(entity);
    }

    /**
     * 获取所有
     *
     * @return
     */
    @Api
    @Override
    public List<Category> getAll() {
        return service.getAll();
    }

    /**
     * 获取一级子类
     *
     * @return
     */
    @Api
    @Override
    public List<Category> children(@PathVariable("id") String id) {
        return service.children(id);
    }

    @Api
    @Override
    public List<Category> getTreeByType(@RequestParam("type") String type) {
        return service.getTreeByType(type);
    }

    @Api
    @Override
    public List<Category> getTreeByTypeAndProject(@RequestParam("type") String type, @RequestParam("projectId") String projectId) {
        return null;
    }

    @Api
    @Override
    public List<Category> getTreeById(@PathVariable("id") String id) {
        return service.getTreeById(id);
    }

    @Api
    @Override
    public Map<String, String> getNameMap() {
        return service.getNameMap();
    }
}
