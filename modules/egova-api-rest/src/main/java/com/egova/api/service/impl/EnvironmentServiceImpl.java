package com.egova.api.service.impl;

import com.egova.api.condition.EnvironmentCondition;
import com.egova.api.domain.EnvironmentRepository;
import com.egova.api.entity.Environment;
import com.egova.api.service.EnvironmentService;
import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
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
public class EnvironmentServiceImpl extends TemplateService<Environment, String> implements EnvironmentService {

    private final EnvironmentRepository environmentRepository;

    @Override
    protected AbstractRepositoryBase<Environment, String> getRepository() {
        return environmentRepository;
    }

    @Override
    public PageResult<Environment> page(QueryModel<EnvironmentCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
