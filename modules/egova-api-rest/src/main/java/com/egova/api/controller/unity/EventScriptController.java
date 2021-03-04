package com.egova.api.controller.unity;

import com.egova.web.annotation.Api;
import com.egova.web.annotation.RequestDecorating;
import com.egova.api.condition.EventScriptCondition;
import com.egova.api.entity.EventScript;
import com.egova.api.facade.EventScriptFacade;
import com.egova.api.service.EventScriptService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by huangkang
 */
@Slf4j
@RestController
@RequestMapping("/unity/event-script")
@RequiredArgsConstructor
public class EventScriptController implements EventScriptFacade {

    private final EventScriptService eventScriptService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return EventScript
     */
    @Api
    @Override
    public EventScript seekById(@PathVariable String id) {
        return eventScriptService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity 事件脚本
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody EventScript entity) {
        return eventScriptService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity 事件脚本
     */
    @Api
    @Override
    public void update(@RequestBody EventScript entity) {
        eventScriptService.update(entity);
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
        return eventScriptService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<EventScript> page(@RequestBody QueryModel<EventScriptCondition> model) {
        return eventScriptService.page(model);
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
        return eventScriptService.deleteByIds(ids);
    }

}
