package com.egova.api.facade;

import com.egova.api.entity.FieldMapping;
import com.egova.api.model.FileldMappingModel;
import com.egova.cloud.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by huangkang
 */
@FeignClient(value = "${service.egova-api:egova-api}", path = "/unity/api/field-mapping")
public interface FieldMappingFacade {

    /**
     * 主键查询
     *
     * @param id 主键
     * @return FieldMapping
     */
    @GetMapping(value = "/{id}")
    FieldMapping seekById(@PathVariable("id") String id);

    /**
     * 保存
     *
     * @param entity api结果转换字段映像
     * @return 主键
     */
    @PostMapping
    String insert(@RequestBody FieldMapping entity);

    /**
     * 更新
     *
     * @param entity api结果转换字段映像
     */
    @PutMapping
    void update(@RequestBody FieldMapping entity);

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @DeleteMapping(value = "/{id}")
    int deleteById(@PathVariable("id") String id);

    /**
     * 主键删除
     *
     * @param apiId
     * @return List<FieldMapping>
     */
    @GetMapping(value = "/apiId/{apiId}")
    List<FieldMapping> apiId(@PathVariable("apiId") String apiId);


    /**
     * 主键删除
     *
     * @param json
     * @param root 根节点
     * @param collapse 是否收缩成通配符[*]
     * @return List<FieldMapping>
     */
    @PostMapping(value = "/parse/fields")
    List<FieldMapping> parse(@RequestParam("json") String json,
                             @RequestParam(value = "root",required = false) String root,
                             @RequestParam(value = "collapse",required = false) Boolean collapse);

    /**
     * 主键删除
     *
     * @param json
     * @param root 根节点
     * @param collapse 是否收缩成通配符[*]
     * @return List<FieldMapping>
     */
    @PostMapping(value = "/parse/model")
    FileldMappingModel parseModel(@RequestParam("json") String json,
                                  @RequestParam(value = "root",required = false) String root,
                                  @RequestParam(value = "collapse",required = false) Boolean collapse);

    /**
     * 主键删除
     *
     * @param model
     * @return List<FieldMapping>
     */
    @PostMapping(value = "/insert")
    void insert(@RequestBody FileldMappingModel model);

}
