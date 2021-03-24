package com.egova.api.controller.free;

import com.egova.api.condition.InfoCondition;
import com.egova.api.entity.Info;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.service.ApiRunService;
import com.egova.api.service.InfoService;
import com.egova.entity.Category;
import com.egova.web.annotation.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * API接口
 *
 * @author 奔波儿灞
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/free/api/info")
public class InfoController {

    private final InfoService infoService;
    private final ApiRunService apiRunService;

    @Api
    @GetMapping("/tree")
    public List<Category> tree(@RequestParam String projectId, @RequestParam(required = false) String categoryId) {
        return infoService.tree(categoryId, projectId);
    }

    @Api
    @PostMapping("/list")
    public List<Info> list(@RequestBody InfoCondition condition) {
        return infoService.list(condition);
    }

    @Api
    @GetMapping("/{id}/whole")
    public ApiInfoModel getApiInfoModel(@PathVariable("id") String id) {
        return infoService.getApiInfoModel(id);
    }

    @Api
    @PostMapping("/{id}/run")
    public String run(@PathVariable String id, @RequestBody HashMap<String, Object> map) {
        return apiRunService.run(id, map);
    }

}
