package com.egova.api.controller.unity;

import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.ConvertConfigCondition;
import com.egova.api.entity.ConvertConfig;
import com.egova.api.facade.ConvertConfigFacade;
import com.egova.api.service.ConvertConfigService;
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
 * created by 迷途小码农
 */
@Slf4j
@RestController
@RequestMapping("/unity/api/convert-config")
@RequiredArgsConstructor
public class ConvertConfigController implements ConvertConfigFacade {

    private final ConvertConfigService convertConfigService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return ConvertConfig
     */
    @Api
    @Override
    public ConvertConfig seekById(@PathVariable String id) {
        return convertConfigService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity 
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody ConvertConfig entity) {
        return convertConfigService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity 
     */
    @Api
    @Override
    public void update(@RequestBody ConvertConfig entity) {
        convertConfigService.update(entity);
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
        return convertConfigService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<ConvertConfig> page(@RequestBody QueryModel<ConvertConfigCondition> model) {
        return convertConfigService.page(model);
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
        return convertConfigService.deleteByIds(ids);
    }

}
