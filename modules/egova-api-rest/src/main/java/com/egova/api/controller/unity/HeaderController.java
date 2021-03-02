package com.egova.api.controller.unity;

import com.egova.api.condition.HeaderCondition;
import com.egova.api.entity.Header;
import com.egova.api.facade.HeaderFacade;
import com.egova.api.service.HeaderService;
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
@RequestMapping("/unity/header")
@RequiredArgsConstructor
public class HeaderController implements HeaderFacade {

    private final HeaderService headerService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return Header
     */
    @Api
    @Override
    public Header seekById(@PathVariable String id) {
        return headerService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api头信息
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody Header entity) {
        return headerService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api头信息
     */
    @Api
    @Override
    public void update(@RequestBody Header entity) {
        headerService.update(entity);
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
        return headerService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<Header> page(@RequestBody QueryModel<HeaderCondition> model) {
        return headerService.page(model);
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
        return headerService.deleteByIds(ids);
    }

}
