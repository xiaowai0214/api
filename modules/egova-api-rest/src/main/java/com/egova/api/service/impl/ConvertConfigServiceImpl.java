package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.ConvertConfigCondition;
import com.egova.api.domain.ConvertConfigRepository;
import com.egova.api.entity.ConvertConfig;
import com.egova.api.service.ConvertConfigService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;

/**
 * created by 迷途小码农
 */
@Slf4j
@Service
@Priority(5)
@RequiredArgsConstructor
public class ConvertConfigServiceImpl extends TemplateService<ConvertConfig, String> implements ConvertConfigService {

    private final ConvertConfigRepository convertConfigRepository;

    @Override
    protected AbstractRepositoryBase<ConvertConfig, String> getRepository() {
        return convertConfigRepository;
    }

    @Override
    public PageResult<ConvertConfig> page(QueryModel<ConvertConfigCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
