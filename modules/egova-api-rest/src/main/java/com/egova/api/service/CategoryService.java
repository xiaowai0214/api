package com.egova.api.service;

import com.egova.api.condition.AdjustSortCondition;
import com.egova.api.condition.CategoryCondition;
import com.egova.api.entity.Category;
import com.egova.api.facade.ApiCategoryFacade;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;

import java.util.List;

public interface CategoryService extends ApiCategoryFacade {

    /**
     * 根据ID数组删除对象
     *
     * @return OperateResult<Boolean> 返回类型
     */
    int deleteByIds(String... ids);



    /**
     * 查询数据
     *
     * @return OperateResult<List < T>> 返回类型
     */
    List<Category> query(CategoryCondition condition);

    /**
     * 根据条件统计数据
     *
     * @return OperateResult<Long> 返回类型
     */
    Long count(CategoryCondition condition);

    /**
     * 根据条件统计数据
     *
     * @return OperateResult<Boolean> 判断编码是否存在
     */
    Boolean conflict(String code);

    /**
     * 分页查询
     *
     * @return OperateResult<List < T>> 返回类型
     */
    PageResult<Category> page(QueryModel<CategoryCondition> query);

//    /**
//     * 根据Id获取 缓存的 路口信息，没有则返回 null
//     *
//     * @return User    返回类型
//     */
//    Category id(String id);

    /**
     * 同层级调整顺序
     *
     * @param condition
     * @return
     */
    Boolean adjustSort(AdjustSortCondition condition);

}
