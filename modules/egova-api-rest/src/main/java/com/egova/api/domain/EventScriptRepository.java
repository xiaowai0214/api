package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.EventScript;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = EventScript.NAME)
public interface EventScriptRepository extends AbstractRepositoryBase<EventScript, String> {
}
