package com.egova.api.facade;

import com.egova.api.entity.Category;
import com.egova.cloud.FeignClient;
import com.egova.cloud.FeignToken;
import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/category")
public interface ApiCategoryFacade {


    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping(value = "/{id}")
    Category getById(@PathVariable(value = "id") String id);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable(value = "id") String id);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @PostMapping(value = "/list")
    List<Category> getByIds(@RequestBody List<String> ids);

    /**
     * 保存数据
     *
     * @return OperateResult<Boolean> 返回类型
     */
    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @PostMapping
    String insert(@RequestBody Category entity);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @PostMapping(value = "/batch")
    @RequestDecorating("insert")
    void insertList(@RequestBody List<Category> categories);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @PutMapping
    void update(@RequestBody Category entity);

    /**
     * 批量更新
     *
     * @return OperateResult<Boolean> 返回类型
     */
    @Api
    @PostMapping(value = "/batch")
    @RequestDecorating("update")
    void updateList(@RequestBody List<Category> categories);

    /**
     * 获取所有
     *
     * @return List<Category>
     */
    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping("/")
    List<Category> getAll();

    /**
     * @return Category 类型
     * @Description: 返回一级子类
     */
    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping("/{id}/children")
    List<Category> children(@PathVariable("id") String id);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping("/list")
    List<Category> getListByType(@RequestParam(value = "type") String type);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping("/tree")
    List<Category> getTreeByType(@RequestParam(value = "type") String type);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping("/project/tree")
    List<Category> getTreeByTypeAndProject(@RequestParam(value = "type") String type, @RequestParam(value = "projectId") String projectId);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping("/{id}/tree")
    List<Category> getTreeById(@PathVariable(value = "id") String id);

    @Api
    @FeignToken(obtain = FeignToken.Obtain.client)
    @GetMapping("/name-map")
    Map<String, String> getNameMap();
}
