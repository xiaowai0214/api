package com.egova.api.controller.unity;

import com.egova.security.UserContext;
import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.TrendsCondition;
import com.egova.api.entity.Trends;
import com.egova.api.facade.TrendsFacade;
import com.egova.api.service.TrendsService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * created by huangkang
 */
@Slf4j
@RestController
@RequestMapping("/unity/api/trends")
@RequiredArgsConstructor
public class TrendsController implements TrendsFacade {

    private final TrendsService trendsService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return Trends
     */
    @Api
    @Override
    public Trends seekById(@PathVariable String id) {
        return trendsService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api动态
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody Trends entity) {
        entity.setCreator(UserContext.username());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return trendsService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api动态
     */
    @Api
    @Override
    public void update(@RequestBody Trends entity) {
        trendsService.update(entity);
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
        return trendsService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    @PostMapping("/page")
    public PageResult<Trends> page(@RequestBody QueryModel<TrendsCondition> model) {
        return trendsService.page(model);
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
        return trendsService.deleteByIds(ids);
    }

}
