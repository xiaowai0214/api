package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.AppHomeCondition;
import com.egova.api.domain.AppHomeRepository;
import com.egova.api.entity.AppHome;
import com.egova.api.service.AppHomeService;
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
public class AppHomeServiceImpl extends TemplateService<AppHome, String> implements AppHomeService {

    private final AppHomeRepository appHomeRepository;

    @Override
    protected AbstractRepositoryBase<AppHome, String> getRepository() {
        return appHomeRepository;
    }

    @Override
    public PageResult<AppHome> page(QueryModel<AppHomeCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
