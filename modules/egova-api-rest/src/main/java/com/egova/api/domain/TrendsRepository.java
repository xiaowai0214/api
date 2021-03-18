package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.Trends;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Trends.NAME)
public interface TrendsRepository extends AbstractRepositoryBase<Trends, String> {
}
