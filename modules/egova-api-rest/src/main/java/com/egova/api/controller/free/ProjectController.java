package com.egova.api.controller.free;

import com.egova.api.condition.ProjectCondition;
import com.egova.api.entity.Project;
import com.egova.api.service.ProjectService;
import com.egova.web.annotation.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目
 *
 * @author 奔波儿灞
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/free/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Api
    @PostMapping("/list")
    public List<Project> list(@RequestBody ProjectCondition condition) {
        return projectService.query(condition);
    }

}
