package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.EventScriptCondition;
import com.egova.api.domain.EventScriptRepository;
import com.egova.api.entity.EventScript;
import com.egova.api.service.EventScriptService;
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
public class EventScriptServiceImpl extends TemplateService<EventScript, String> implements EventScriptService {

    private final EventScriptRepository eventScriptRepository;

    @Override
    protected AbstractRepositoryBase<EventScript, String> getRepository() {
        return eventScriptRepository;
    }

    @Override
    public PageResult<EventScript> page(QueryModel<EventScriptCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
