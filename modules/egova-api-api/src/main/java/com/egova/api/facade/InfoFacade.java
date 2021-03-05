package com.egova.api.facade;

import com.egova.api.model.ApiInfoModel;
import com.egova.cloud.FeignClient;
import com.egova.api.entity.Info;
import com.egova.entity.Category;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/info")
public interface InfoFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return Info
     */
    @GetMapping(value = "/{id}")
    Info seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity api信息
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody Info entity);

    /**
     * 更新
     *
     * @param entity api信息
     */
    @PutMapping
    void update(@RequestBody Info entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

    /**
     * 主键删除
     *
     * @param categoryId
     * @param projectId
     * @return List<Info>
     */
    @GetMapping(value = "/tree/{projectId}")
    List<Category> tree(@RequestParam(value = "categoryId", required = false) String categoryId, @PathVariable("projectId") String projectId);

    /**
     * 修改
     *
     * @param id
     * @param map
     * @return 主键
     */
    @PostMapping("/modify/{id}")
    void modify(@PathVariable("id") String id , @RequestBody HashMap<String,Object> map);


    /**
     * 更新
     *
     * @param apiInfoModel api信息
     */
    @PutMapping("/save/whole")
    void update(@RequestBody ApiInfoModel apiInfoModel);

}
