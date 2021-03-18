package com.egova.api.service.impl;

import com.egova.api.condition.ProjectCondition;
import com.egova.api.domain.ProjectRepository;
import com.egova.api.entity.Project;
import com.egova.api.entity.codes.ProjectIntro;
import com.egova.api.service.ProjectService;
import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.egova.security.UserContext;
import com.flagwind.persistent.model.Paging;
import com.flagwind.persistent.model.Sorting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
        if (model.getPaging() == null){
            model.setPaging(new Paging(1L,10L));
        }
        if (model.getSorts() == null){
            model.setSorts(new Sorting[]{Sorting.descending("createTime")});
        }
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

    @Override
    public List<Project> query(ProjectCondition condition) {
        return super.query(condition,new Sorting[]{Sorting.descending("createTime")});
    }

    @Override
    public void update(Project entity) {
        super.update(entity);
        ProjectIntro.of(entity.getId()).invalid();
    }

    @Override
    public String insert(Project entity) {
        entity.setCreator(UserContext.username());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return super.insert(entity);
    }

    @Override
    public Map<String, String> getNameMap() {
        return this.map("id", "name");
    }
}
