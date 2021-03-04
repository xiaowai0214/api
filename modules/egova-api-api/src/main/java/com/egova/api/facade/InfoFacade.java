package com.egova.api.facade;

import com.egova.cloud.FeignClient;
import com.egova.api.entity.Info;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/info")
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

}
