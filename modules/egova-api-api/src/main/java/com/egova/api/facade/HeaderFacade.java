package com.egova.api.facade;

import com.egova.api.entity.Header;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/header")
public interface HeaderFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return Header
     */
    @GetMapping(value = "/{id}")
    Header seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity api头信息
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody Header entity);

    /**
     * 更新
     *
     * @param entity api头信息
     */
    @PutMapping
    void update(@RequestBody Header entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

}
