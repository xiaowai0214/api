package com.egova.api.facade;

import com.egova.api.entity.Project;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/project")
public interface ProjectFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return Project
     */
    @GetMapping(value = "/{id}")
    Project seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity api项目表
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody Project entity);

    /**
     * 更新
     *
     * @param entity api项目表
     */
    @PutMapping
    void update(@RequestBody Project entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

}
