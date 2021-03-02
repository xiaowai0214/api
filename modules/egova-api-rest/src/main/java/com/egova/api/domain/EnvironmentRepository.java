package com.egova.api.domain;

import com.egova.api.entity.Environment;
import com.egova.data.service.AbstractRepositoryBase;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Environment.NAME)
public interface EnvironmentRepository extends AbstractRepositoryBase<Environment, String> {
}
