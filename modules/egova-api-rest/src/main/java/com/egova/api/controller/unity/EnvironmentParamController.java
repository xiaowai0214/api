package com.egova.api.controller.unity;

import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.EnvironmentParamCondition;
import com.egova.api.entity.EnvironmentParam;
import com.egova.api.facade.EnvironmentParamFacade;
import com.egova.api.service.EnvironmentParamService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by huangkang
 */
@Slf4j
@RestController
@RequestMapping("/unity/api/environment-param")
@RequiredArgsConstructor
public class EnvironmentParamController implements EnvironmentParamFacade {

    private final EnvironmentParamService environmentParamService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return EnvironmentParam
     */
    @Api
    @Override
    public EnvironmentParam seekById(@PathVariable String id) {
        return environmentParamService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity 环境变量
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody EnvironmentParam entity) {
        return environmentParamService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity 环境变量
     */
    @Api
    @Override
    public void update(@RequestBody EnvironmentParam entity) {
        environmentParamService.update(entity);
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
        return environmentParamService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<EnvironmentParam> page(@RequestBody QueryModel<EnvironmentParamCondition> model) {
        return environmentParamService.page(model);
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
        return environmentParamService.deleteByIds(ids);
    }

}
