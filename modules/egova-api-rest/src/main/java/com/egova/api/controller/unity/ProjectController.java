package com.egova.api.controller.unity;

import com.egova.api.condition.ProjectCondition;
import com.egova.api.entity.Project;
import com.egova.api.facade.ProjectFacade;
import com.egova.api.service.ProjectService;
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
@RequestMapping("/unity/api/project")
@RequiredArgsConstructor
public class ProjectController implements ProjectFacade {

    private final ProjectService projectService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return Project
     */
    @Api
    @Override
    public Project seekById(@PathVariable String id) {
        return projectService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api项目表
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody Project entity) {
        return projectService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api项目表
     */
    @Api
    @Override
    public void update(@RequestBody Project entity) {
        projectService.update(entity);
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
        return projectService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    @PostMapping("/page")
    public PageResult<Project> page(@RequestBody QueryModel<ProjectCondition> model) {
        return projectService.page(model);
    }

    /**
     * 分页查询
     *
     * @param condition ProjectCondition
     * @return List
     */
    @Api
    @PostMapping("/list")
    public List<Project> query(@RequestBody ProjectCondition condition) {
        return projectService.query(condition);
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
        return projectService.deleteByIds(ids);
    }

}
