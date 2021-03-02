package com.egova.api.controller.unity;

import com.egova.api.condition.EnvironmentCondition;
import com.egova.api.entity.Environment;
import com.egova.api.facade.EnvironmentFacade;
import com.egova.api.service.EnvironmentService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by huangkang
 */
@Slf4j
@RestController
@RequestMapping("/unity/environment")
@RequiredArgsConstructor
public class EnvironmentController implements EnvironmentFacade {

    private final EnvironmentService environmentService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return Environment
     */
    @Api
    @Override
    public Environment seekById(@PathVariable String id) {
        return environmentService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity 
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody Environment entity) {
        return environmentService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity 
     */
    @Api
    @Override
    public void update(@RequestBody Environment entity) {
        environmentService.update(entity);
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
        return environmentService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<Environment> page(@RequestBody QueryModel<EnvironmentCondition> model) {
        return environmentService.page(model);
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
        return environmentService.deleteByIds(ids);
    }

}
