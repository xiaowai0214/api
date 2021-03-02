package com.egova.api.controller.unity;

import com.egova.api.condition.ApiCondition;
import com.egova.api.facade.ApiFacade;
import com.egova.api.service.ApiService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
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
@RequestMapping("/unity/api")
@RequiredArgsConstructor
public class ApiController implements ApiFacade {

    private final ApiService apiService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return Api
     */
    @Api
    @Override
    public Api seekById(@PathVariable String id) {
        return apiService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity 
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody Api entity) {
        return apiService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity 
     */
    @Api
    @Override
    public void update(@RequestBody Api entity) {
        apiService.update(entity);
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
        return apiService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<Api> page(@RequestBody QueryModel<ApiCondition> model) {
        return apiService.page(model);
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
        return apiService.deleteByIds(ids);
    }

}
