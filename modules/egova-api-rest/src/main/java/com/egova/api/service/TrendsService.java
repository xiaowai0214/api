package com.egova.api.service;

import com.egova.api.condition.TrendsCondition;
import com.egova.api.entity.Trends;
import com.egova.api.facade.TrendsFacade;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;

import java.util.List;

/**
 * created by huangkang
 */
public interface TrendsService extends TrendsFacade {

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return 分页数据
     */
    PageResult<Trends> page(QueryModel<TrendsCondition> model);

    /**
     * 主键批量删除
     *
     * @param ids 主键
     * @return 影响记录行数
     */
    int deleteByIds(List<String> ids);

}
