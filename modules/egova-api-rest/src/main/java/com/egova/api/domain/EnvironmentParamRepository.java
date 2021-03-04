package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.EnvironmentParam;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = EnvironmentParam.NAME)
public interface EnvironmentParamRepository extends AbstractRepositoryBase<EnvironmentParam, String> {
}
