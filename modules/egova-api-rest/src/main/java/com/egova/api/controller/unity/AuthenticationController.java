package com.egova.api.controller.unity;

import com.egova.api.condition.AuthenticationCondition;
import com.egova.api.entity.Authentication;
import com.egova.api.facade.AuthenticationFacade;
import com.egova.api.service.AuthenticationService;
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
@RequestMapping("/unity/authentication")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationFacade {

    private final AuthenticationService authenticationService;

    /**
     * 主键获取
     *
     * @param id 主键
     * @return Authentication
     */
    @Api
    @Override
    public Authentication seekById(@PathVariable String id) {
        return authenticationService.seekById(id);
    }

    /**
     * 保存
     *
     * @param entity api鉴权
     * @return 主键
     */
    @Api
    @Override
    public String insert(@RequestBody Authentication entity) {
        return authenticationService.insert(entity);
    }

    /**
     * 更新
     *
     * @param entity api鉴权
     */
    @Api
    @Override
    public void update(@RequestBody Authentication entity) {
        authenticationService.update(entity);
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
        return authenticationService.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param model QueryModel
     * @return PageResult
     */
    @Api
    public PageResult<Authentication> page(@RequestBody QueryModel<AuthenticationCondition> model) {
        return authenticationService.page(model);
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
        return authenticationService.deleteByIds(ids);
    }

}
