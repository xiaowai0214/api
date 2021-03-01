package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.AppHome;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = AppHome.NAME)
public interface AppHomeRepository extends AbstractRepositoryBase<AppHome, String> {
}
