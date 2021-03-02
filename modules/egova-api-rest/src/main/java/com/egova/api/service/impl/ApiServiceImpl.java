package com.egova.api.service.impl;

import com.egova.api.condition.ApiCondition;
import com.egova.api.domain.ApiRepository;
import com.egova.api.entity.Api;
import com.egova.api.service.ApiService;
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
public class ApiServiceImpl extends TemplateService<Api, String> implements ApiService {

    private final ApiRepository apiRepository;

    @Override
    protected AbstractRepositoryBase<Api, String> getRepository() {
        return apiRepository;
    }

    @Override
    public PageResult<Api> page(QueryModel<ApiCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
