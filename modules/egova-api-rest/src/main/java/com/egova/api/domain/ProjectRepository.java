package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.Project;
import org.springframework.cache.annotation.CacheConfig;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Project.NAME)
public interface ProjectRepository extends AbstractRepositoryBase<Project, String> {
}
