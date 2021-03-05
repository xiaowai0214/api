package com.egova.api.controller.unity;

import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.RequestParamCondition;
import com.egova.api.entity.RequestParam;
import com.egova.api.facade.RequestParamFacade;
import com.egova.api.service.RequestParamService;
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
@RequestMapping("/unity/api/request-param")
@RequiredArgsConstructor
public class RequestParamController implements RequestParamFacade {

    private final RequestParamService requestParamService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return RequestParam
     */
    @Api
    @Override
    public RequestParam seekById(@PathVariable String id) {
        return requestParamService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api请求参数
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody RequestParam entity) {
        return requestParamService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api请求参数
     */
    @Api
    @Override
    public void update(@RequestBody RequestParam entity) {
        requestParamService.update(entity);
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
        return requestParamService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<RequestParam> page(@RequestBody QueryModel<RequestParamCondition> model) {
        return requestParamService.page(model);
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
        return requestParamService.deleteByIds(ids);
    }

}
