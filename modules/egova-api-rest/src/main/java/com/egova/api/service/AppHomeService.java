package com.egova.api.service;

import com.egova.api.condition.AppHomeCondition;
import com.egova.api.entity.AppHome;
import com.egova.api.facade.AppHomeFacade;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;

import java.util.List;

/**
 * created by huangkang
 */
public interface AppHomeService extends AppHomeFacade {

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return 分页数据
     */
    PageResult<AppHome> page(QueryModel<AppHomeCondition> model);

    /**
     * 主键批量删除
     *
     * @param ids 主键
     * @return 影响记录行数
     */
    int deleteByIds(List<String> ids);

}
