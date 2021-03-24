package com.egova.api.facade;

import com.egova.api.model.ApiInfoModel;
import com.egova.api.model.ApiResult;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/run")
public interface ApiRunFacade {


    /**
     * 运行
     *
     * @param apiId apiId
     * @param model api项目表
     * @return 主键
     */

    @PostMapping("/{apiId}")
    String run(@PathVariable("apiId") String apiId , @RequestBody ApiInfoModel model);

    /**
     * 运行
     *
     * @param apiId apiId
     * @param map 参数
     * @return 主键
     */

    @PostMapping("/{apiId}/data-center")
    String run(@PathVariable("apiId") String apiId , @RequestBody HashMap<String,Object> map);

    /**
     * 运行
     *
     * @param apiId apiId
     * @param model api项目表
     * @return 主键
     */

    @PostMapping("/{apiId}/web")
    ApiResult runWeb(@PathVariable("apiId") String apiId , @RequestBody ApiInfoModel model);


    /**
     * 获取输出转换格式，给ETL
     *
     * @param apiId apiId
     * @return 主键
     */
    @GetMapping("/output/format/{apiId}")
    Object outputFormat(@PathVariable("apiId") String apiId);


    /**
     * 获取输入参数的jsonpath
     *
     * @param json
     * @return 主键
     */
//    @GetMapping("/json/path")
//    Object jsonPath(@RequestParam("json") String json);
}
