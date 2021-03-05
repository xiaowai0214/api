package com.egova.api.controller.unity;

import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.RequestHeaderCondition;
import com.egova.api.entity.RequestHeader;
import com.egova.api.facade.RequestHeaderFacade;
import com.egova.api.service.RequestHeaderService;
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
@RequestMapping("/unity/api/request-header")
@RequiredArgsConstructor
public class RequestHeaderController implements RequestHeaderFacade {

    private final RequestHeaderService requestHeaderService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return RequestHeader
     */
    @Api
    @Override
    public RequestHeader seekById(@PathVariable String id) {
        return requestHeaderService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api头信息
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody RequestHeader entity) {
        return requestHeaderService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api头信息
     */
    @Api
    @Override
    public void update(@RequestBody RequestHeader entity) {
        requestHeaderService.update(entity);
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
        return requestHeaderService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<RequestHeader> page(@RequestBody QueryModel<RequestHeaderCondition> model) {
        return requestHeaderService.page(model);
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
        return requestHeaderService.deleteByIds(ids);
    }

}
