package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.ProjectCondition;
import com.egova.api.domain.ProjectRepository;
import com.egova.api.entity.Project;
import com.egova.api.service.ProjectService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;

/**
 * created by huangkang
 */
@Slf4j
@Service
@Priority(5)
@RequiredArgsConstructor
public class ProjectServiceImpl extends TemplateService<Project, String> implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    protected AbstractRepositoryBase<Project, String> getRepository() {
        return projectRepository;
    }

    @Override
    public PageResult<Project> page(QueryModel<ProjectCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
