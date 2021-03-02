package com.egova.api.domain;

import com.egova.api.entity.RequestParam;
import com.egova.data.service.AbstractRepositoryBase;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = RequestParam.NAME)
public interface RequestParamRepository extends AbstractRepositoryBase<RequestParam, String> {
}
