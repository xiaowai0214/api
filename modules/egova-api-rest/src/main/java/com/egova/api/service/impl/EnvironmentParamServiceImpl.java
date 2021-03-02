package com.egova.api.service.impl;

import com.egova.api.condition.EnvironmentParamCondition;
import com.egova.api.domain.EnvironmentParamRepository;
import com.egova.api.entity.EnvironmentParam;
import com.egova.api.service.EnvironmentParamService;
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
public class EnvironmentParamServiceImpl extends TemplateService<EnvironmentParam, String> implements EnvironmentParamService {

    private final EnvironmentParamRepository environmentParamRepository;

    @Override
    protected AbstractRepositoryBase<EnvironmentParam, String> getRepository() {
        return environmentParamRepository;
    }

    @Override
    public PageResult<EnvironmentParam> page(QueryModel<EnvironmentParamCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
