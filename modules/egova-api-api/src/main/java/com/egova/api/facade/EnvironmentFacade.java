package com.egova.api.facade;

import com.egova.api.entity.Environment;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/environment")
public interface EnvironmentFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return Environment
     */
    @GetMapping(value = "/{id}")
    Environment seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity 
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody Environment entity);

    /**
     * 更新
     *
     * @param entity 
     */
    @PutMapping
    void update(@RequestBody Environment entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

}
