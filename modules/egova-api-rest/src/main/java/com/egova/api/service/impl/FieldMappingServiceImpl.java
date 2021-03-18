package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.FieldMappingCondition;
import com.egova.api.domain.FieldMappingRepository;
import com.egova.api.entity.FieldMapping;
import com.egova.api.service.FieldMappingService;
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
public class FieldMappingServiceImpl extends TemplateService<FieldMapping, String> implements FieldMappingService {

    private final FieldMappingRepository fieldMappingRepository;

    @Override
    protected AbstractRepositoryBase<FieldMapping, String> getRepository() {
        return fieldMappingRepository;
    }

    @Override
    public PageResult<FieldMapping> page(QueryModel<FieldMappingCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
