package com.egova.api.service;

import com.egova.api.condition.ConvertConfigCondition;
import com.egova.api.entity.ConvertConfig;
import com.egova.api.facade.ConvertConfigFacade;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;

import java.util.List;

/**
 * created by 迷途小码农
 */
public interface ConvertConfigService extends ConvertConfigFacade {

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return 分页数据
     */
    PageResult<ConvertConfig> page(QueryModel<ConvertConfigCondition> model);

    /**
     * 主键批量删除
     *
     * @param ids 主键
     * @return 影响记录行数
     */
    int deleteByIds(List<String> ids);

}
