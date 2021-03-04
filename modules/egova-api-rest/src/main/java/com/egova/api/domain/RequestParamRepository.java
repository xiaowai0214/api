package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.RequestParam;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = RequestParam.NAME)
public interface RequestParamRepository extends AbstractRepositoryBase<RequestParam, String> {
}
