package com.egova.api.controller.free;

import com.egova.api.condition.InfoCondition;
import com.egova.api.entity.Category;
import com.egova.api.entity.FieldMapping;
import com.egova.api.entity.Info;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.service.ApiRunService;
import com.egova.api.service.FieldMappingService;
import com.egova.api.service.InfoService;
import com.egova.web.annotation.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    private final FieldMappingService fieldMappingService;

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

    @Api
    @GetMapping("/{id}/fields")
    public List<FieldMapping> fields(@PathVariable String id) {
        return fieldMappingService.apiId(id);
    }

}
