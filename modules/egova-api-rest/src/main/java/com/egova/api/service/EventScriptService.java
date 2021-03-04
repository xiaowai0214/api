package com.egova.api.service;

import com.egova.api.condition.EventScriptCondition;
import com.egova.api.entity.EventScript;
import com.egova.api.facade.EventScriptFacade;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;

import java.util.List;

/**
 * created by huangkang
 */
public interface EventScriptService extends EventScriptFacade {

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return 分页数据
     */
    PageResult<EventScript> page(QueryModel<EventScriptCondition> model);

    /**
     * 主键批量删除
     *
     * @param ids 主键
     * @return 影响记录行数
     */
    int deleteByIds(List<String> ids);

}
