package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.RequestHeaderCondition;
import com.egova.api.domain.RequestHeaderRepository;
import com.egova.api.entity.RequestHeader;
import com.egova.api.service.RequestHeaderService;
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
public class RequestHeaderServiceImpl extends TemplateService<RequestHeader, String> implements RequestHeaderService {

    private final RequestHeaderRepository requestHeaderRepository;

    @Override
    protected AbstractRepositoryBase<RequestHeader, String> getRepository() {
        return requestHeaderRepository;
    }

    @Override
    public PageResult<RequestHeader> page(QueryModel<RequestHeaderCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
