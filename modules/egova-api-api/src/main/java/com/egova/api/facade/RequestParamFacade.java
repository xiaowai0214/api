package com.egova.api.facade;

import com.egova.api.entity.RequestParam;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/request-param")
public interface RequestParamFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return RequestParam
     */
    @GetMapping(value = "/{id}")
    RequestParam seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity api请求参数
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody RequestParam entity);

    /**
     * 更新
     *
     * @param entity api请求参数
     */
    @PutMapping
    void update(@RequestBody RequestParam entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);


    /**
     * 主键查询
     *
     * @param apiId
     * @return List<RequestParam>
     */
    @GetMapping(value = "/apiId/{apiId}")
    List<RequestParam> apiId(@PathVariable("apiId") String apiId);

}
