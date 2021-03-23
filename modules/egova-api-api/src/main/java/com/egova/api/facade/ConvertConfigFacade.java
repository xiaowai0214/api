package com.egova.api.facade;

import com.egova.cloud.FeignClient;
import com.egova.api.entity.ConvertConfig;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * created by 迷途小码农
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/convert-config")
public interface ConvertConfigFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return ConvertConfig
     */
    @GetMapping(value = "/{id}")
    ConvertConfig seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity 
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody ConvertConfig entity);

    /**
     * 更新
     *
     * @param entity 
     */
    @PutMapping
    void update(@RequestBody ConvertConfig entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

}
