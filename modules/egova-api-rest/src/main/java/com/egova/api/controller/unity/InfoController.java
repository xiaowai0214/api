package com.egova.api.controller.unity;

import com.egova.api.model.ApiInfoModel;
import com.egova.entity.Category;
import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.InfoCondition;
import com.egova.api.entity.Info;
import com.egova.api.facade.InfoFacade;
import com.egova.api.service.InfoService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * created by huangkang
 */
@Slf4j
@RestController
@RequestMapping("/unity/info")
@RequiredArgsConstructor
public class InfoController implements InfoFacade {

    private final InfoService infoService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return Info
     */
    @Api
    @Override
    public Info seekById(@PathVariable String id) {
        return infoService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api信息
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody Info entity) {
        return infoService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api信息
     */
    @Api
    @Override
    public void update(@RequestBody Info entity) {
        infoService.update(entity);
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
        return infoService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<Info> page(@RequestBody QueryModel<InfoCondition> model) {
        return infoService.page(model);
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
        return infoService.deleteByIds(ids);
    }

    @Api
    @Override
    public List<Category> tree(@RequestParam String categoryId, @PathVariable String projectId) {
        return infoService.tree(categoryId,projectId);
    }

    @Api
    @Override
    public void modify(@PathVariable String id, HashMap<String, Object> map) {
        infoService.modify(id,map);
    }

    @Api
    @Override
    public void update(ApiInfoModel apiInfoModel) {
        infoService.update(apiInfoModel);
    }
}
