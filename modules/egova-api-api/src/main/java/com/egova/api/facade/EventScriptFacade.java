package com.egova.api.facade;

import com.egova.cloud.FeignClient;
import com.egova.api.entity.EventScript;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/event-script")
public interface EventScriptFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return EventScript
     */
    @GetMapping(value = "/{id}")
    EventScript seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity 事件脚本
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody EventScript entity);

    /**
     * 更新
     *
     * @param entity 事件脚本
     */
    @PutMapping
    void update(@RequestBody EventScript entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

}
