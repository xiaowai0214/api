package com.egova.api.controller.unity;

import com.egova.api.model.FileldMappingModel;
import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.FieldMappingCondition;
import com.egova.api.entity.FieldMapping;
import com.egova.api.facade.FieldMappingFacade;
import com.egova.api.service.FieldMappingService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by huangkang
 */
@Slf4j
@RestController
@RequestMapping("/unity/api/field-mapping")
@RequiredArgsConstructor
public class FieldMappingController implements FieldMappingFacade {

    private final FieldMappingService fieldMappingService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return FieldMapping
     */
    @Api
    @Override
    public FieldMapping seekById(@PathVariable String id) {
        return fieldMappingService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api结果转换字段映像
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody FieldMapping entity) {
        return fieldMappingService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api结果转换字段映像
     */
    @Api
    @Override
    public void update(@RequestBody FieldMapping entity) {
        fieldMappingService.update(entity);
    }

    /**
     * 主键删除
     *
     * @param id 主键
     * @return 影响记录行数
     */
    @Api
    @Override
    public int deleteById(@PathVariable String id) {
        return fieldMappingService.deleteById(id);
    }

    @Api
    @Override
    public List<FieldMapping> apiId(@PathVariable String apiId) {
        return fieldMappingService.apiId(apiId);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<FieldMapping> page(@RequestBody QueryModel<FieldMappingCondition> model) {
        return fieldMappingService.page(model);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return 影响记录行数
     */
    @Api
    @PostMapping("/batch")
    @RequestDecorating(value = "delete")
    public int batchDelete(@RequestBody List<String> ids) {
        return fieldMappingService.deleteByIds(ids);
    }

    @Api
    @Override
    public List<FieldMapping> parse(@RequestParam String json, @RequestParam String root) {
        return fieldMappingService.parse(json, root);
    }

    @Api
    @Override
    public FileldMappingModel parseModel(@RequestParam String json, @RequestParam String root) {
        return fieldMappingService.parseModel(json, root);
    }

    @Api
    @Override
    public void insert(@RequestBody FileldMappingModel model) {
        fieldMappingService.insert(model);
    }
}
