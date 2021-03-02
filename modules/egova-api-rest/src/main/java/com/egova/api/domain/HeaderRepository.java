package com.egova.api.domain;

import com.egova.api.entity.Header;
import com.egova.data.service.AbstractRepositoryBase;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Header.NAME)
public interface HeaderRepository extends AbstractRepositoryBase<Header, String> {
}
