package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.TrendsCondition;
import com.egova.api.domain.TrendsRepository;
import com.egova.api.entity.Trends;
import com.egova.api.service.TrendsService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.flagwind.persistent.model.Paging;
import com.flagwind.persistent.model.Sorting;
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
public class TrendsServiceImpl extends TemplateService<Trends, String> implements TrendsService {

    private final TrendsRepository trendsRepository;

    @Override
    protected AbstractRepositoryBase<Trends, String> getRepository() {
        return trendsRepository;
    }

    @Override
    public PageResult<Trends> page(QueryModel<TrendsCondition> model) {
        if (model == null){
            model = new QueryModel<>();
        }
        if (model.getPaging() == null){
            model.setPaging(new Paging(0L,10L));
        }
        if (model.getSorts() == null){
            model.setSorts(new Sorting[]{Sorting.descending("createTime")});
        }
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
