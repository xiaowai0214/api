package com.egova.api.domain;

import com.egova.api.entity.EnvironmentParam;
import com.egova.data.service.AbstractRepositoryBase;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = EnvironmentParam.NAME)
public interface EnvironmentParamRepository extends AbstractRepositoryBase<EnvironmentParam, String> {
}
