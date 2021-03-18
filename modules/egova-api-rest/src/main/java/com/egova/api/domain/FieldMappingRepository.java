package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.FieldMapping;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = FieldMapping.NAME)
public interface FieldMappingRepository extends AbstractRepositoryBase<FieldMapping, String> {
}
