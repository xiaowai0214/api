package com.egova.api.service.impl;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.api.condition.AuthenticationCondition;
import com.egova.api.domain.AuthenticationRepository;
import com.egova.api.entity.Authentication;
import com.egova.api.service.AuthenticationService;
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
public class AuthenticationServiceImpl extends TemplateService<Authentication, String> implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    @Override
    protected AbstractRepositoryBase<Authentication, String> getRepository() {
        return authenticationRepository;
    }

    @Override
    public PageResult<Authentication> page(QueryModel<AuthenticationCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

}
