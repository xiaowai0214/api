package com.egova.api.facade;

import com.egova.api.entity.Api;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api")
public interface ApiFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return Api
     */
    @GetMapping(value = "/{id}")
    Api seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity 
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody Api entity);

    /**
     * 更新
     *
     * @param entity 
     */
    @PutMapping
    void update(@RequestBody Api entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

}
