package com.egova.api.facade;

import com.egova.cloud.FeignClient;
import com.egova.api.entity.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/authentication")
public interface AuthenticationFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return Authentication
     */
    @GetMapping(value = "/{id}")
    Authentication seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity api鉴权
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody Authentication entity);

    /**
     * 更新
     *
     * @param entity api鉴权
     */
    @PutMapping
    void update(@RequestBody Authentication entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

}
