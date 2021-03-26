package com.egova.api.authorization.impl;

import com.egova.api.authorization.AuthenticationSupplier;
import com.egova.api.domain.AuthenticationRepository;
import com.egova.api.domain.InfoRepository;
import com.egova.api.entity.Authentication;
import com.egova.api.entity.Info;
import com.egova.exception.ExceptionUtils;
import com.flagwind.application.Application;

import java.util.Optional;

public abstract class AbstractAuthenticationSupplier implements AuthenticationSupplier {

    public AbstractAuthenticationSupplier() {
    }

    public Authentication getConfig(String apiId){
        InfoRepository infoRepository = Application.resolve(InfoRepository.class);
        AuthenticationRepository authenticationRepository = Application.resolve(AuthenticationRepository.class);
        Info info = Optional.ofNullable(infoRepository.getById(apiId)).orElseThrow(() -> ExceptionUtils.api("api为空"));
        Authentication authentication = Optional.ofNullable(authenticationRepository.single("projectId", info.getProjectId())).orElseThrow(() -> ExceptionUtils.api("授权配置为空"));
        return authentication;
    }
}
