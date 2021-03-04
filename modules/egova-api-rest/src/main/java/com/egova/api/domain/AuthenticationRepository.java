package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.Authentication;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Authentication.NAME)
public interface AuthenticationRepository extends AbstractRepositoryBase<Authentication, String> {
}
