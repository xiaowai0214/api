package com.egova.api.service;

import com.egova.api.condition.RequestParamCondition;
import com.egova.api.entity.RequestParam;
import com.egova.api.facade.RequestParamFacade;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;

import java.util.List;

/**
 * created by huangkang
 */
public interface RequestParamService extends RequestParamFacade {

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return 分页数据
     */
    PageResult<RequestParam> page(QueryModel<RequestParamCondition> model);

    /**
     * 主键批量删除
     *
     * @param ids 主键
     * @return 影响记录行数
     */
    int deleteByIds(List<String> ids);

}
