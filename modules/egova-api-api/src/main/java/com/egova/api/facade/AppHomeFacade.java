package com.egova.api.facade;

import com.egova.cloud.FeignClient;
import com.egova.api.entity.AppHome;
import org.springframework.web.bind.annotation.*;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.api-builder:api-builder}", path = "/unity/app-home")
public interface AppHomeFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return AppHome
     */
    @GetMapping(value = "/{id}")
    AppHome getById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param appHome App应用
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody AppHome appHome);

    /**
     * 更新
     *
     * @param appHome App应用
     */
    @PutMapping
    void update(@RequestBody AppHome appHome);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id")  String id);

}
