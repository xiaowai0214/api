package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.RequestParamCondition;
import com.egova.api.domain.RequestParamRepository;
import com.egova.api.entity.RequestParam;
import com.egova.api.service.RequestParamService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.flagwind.persistent.model.SingleClause;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import java.util.List;

/**
 * created by huangkang
 */
@Slf4j
@Service
@Priority(5)
@RequiredArgsConstructor
public class RequestParamServiceImpl extends TemplateService<RequestParam, String> implements RequestParamService {

    private final RequestParamRepository requestParamRepository;

    @Override
    protected AbstractRepositoryBase<RequestParam, String> getRepository() {
        return requestParamRepository;
    }

    @Override
    public PageResult<RequestParam> page(QueryModel<RequestParamCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

    @Override
    public List<RequestParam> apiId(String apiId) {
        return requestParamRepository.query(SingleClause.equal("apiId",apiId));
    }
}
