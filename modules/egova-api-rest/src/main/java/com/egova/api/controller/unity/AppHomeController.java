package com.egova.api.controller.unity;

import com.egova.api.condition.AppHomeCondition;
import com.egova.api.entity.AppHome;
import com.egova.api.facade.AppHomeFacade;
import com.egova.api.service.AppHomeService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.egova.web.annotation.Api;
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
@RequestMapping("/unity/app-home")
@RequiredArgsConstructor
public class AppHomeController implements AppHomeFacade {

    private final AppHomeService appHomeService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return AppHome
     */
    @Api
    @Override
    public AppHome getById(@PathVariable String id) {
        return appHomeService.getById(id);
    }

    /**
     * 保存
     *
     * @param appHome App应用
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody AppHome appHome) {
        return appHomeService.insert(appHome);
    }

    /**
     * 更新
     *
     * @param appHome App应用
     */
    @Api
    @Override
    public void update(@RequestBody AppHome appHome) {
        appHomeService.update(appHome);
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
        return appHomeService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model 模型
     * @return PageResult
     */
    @Api
    @PostMapping("/page")
    public PageResult<AppHome> page(@RequestBody QueryModel<AppHomeCondition> model) {
        return appHomeService.page(model);
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
        return appHomeService.deleteByIds(ids);
    }

}
