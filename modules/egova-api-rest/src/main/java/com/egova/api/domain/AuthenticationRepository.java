package com.egova.api.domain;

import com.egova.api.entity.Authentication;
import com.egova.data.service.AbstractRepositoryBase;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Authentication.NAME)
public interface AuthenticationRepository extends AbstractRepositoryBase<Authentication, String> {
}
