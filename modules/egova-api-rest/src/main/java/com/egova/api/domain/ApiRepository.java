package com.egova.api.domain;

import com.egova.api.entity.Api;
import com.egova.data.service.AbstractRepositoryBase;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Api.NAME)
public interface ApiRepository extends AbstractRepositoryBase<Api, String> {
}
