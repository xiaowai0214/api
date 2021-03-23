package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.ConvertConfig;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by 迷途小码农
 */
@CacheConfig(cacheNames = ConvertConfig.NAME)
public interface ConvertConfigRepository extends AbstractRepositoryBase<ConvertConfig, String> {
}
