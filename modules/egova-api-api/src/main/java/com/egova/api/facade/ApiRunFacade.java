package com.egova.api.facade;

import com.egova.api.model.ApiInfoModel;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/run")
public interface ApiRunFacade {


    /**
     * 保存
     *
     * @param apiId apiId
     * @param model api项目表
     * @return 主键
     */

    @PostMapping("/{apiId}")
    String run(@PathVariable("apiId") String apiId , @RequestBody ApiInfoModel model);

    /**
     * 获取输出转换格式，给ETL
     *
     * @param apiId apiId
     * @return 主键
     */
    @GetMapping("/output/format/{apiId}")
    Object outputFormat(@PathVariable("apiId") String apiId);
}
