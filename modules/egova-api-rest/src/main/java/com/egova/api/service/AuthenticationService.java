package com.egova.api.service;

import com.egova.api.condition.AuthenticationCondition;
import com.egova.api.entity.Authentication;
import com.egova.api.facade.AuthenticationFacade;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;

import java.util.List;

/**
 * created by huangkang
 */
public interface AuthenticationService extends AuthenticationFacade {

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return 分页数据
     */
    PageResult<Authentication> page(QueryModel<AuthenticationCondition> model);

    /**
     * 主键批量删除
     *
     * @param ids 主键
     * @return 影响记录行数
     */
    int deleteByIds(List<String> ids);

}
