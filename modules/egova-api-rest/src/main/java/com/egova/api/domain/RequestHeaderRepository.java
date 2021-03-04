package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.RequestHeader;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = RequestHeader.NAME)
public interface RequestHeaderRepository extends AbstractRepositoryBase<RequestHeader, String> {
}
