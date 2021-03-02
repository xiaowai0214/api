package com.egova.api.service.impl;

import com.egova.api.condition.HeaderCondition;
import com.egova.api.domain.HeaderRepository;
import com.egova.api.entity.Header;
import com.egova.api.service.HeaderService;
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
public class HeaderServiceImpl extends TemplateService<Header, String> implements HeaderService {

    private final HeaderRepository headerRepository;

    @Override
    protected AbstractRepositoryBase<Header, String> getRepository() {
        return headerRepository;
    }

    @Override
    public PageResult<Header> page(QueryModel<HeaderCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
