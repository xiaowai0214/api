package com.egova.api.domain;

import com.egova.data.service.AbstractRepositoryBase;
import com.egova.api.entity.Info;
import com.egova.persistent.ClauseBuilder;
import com.flagwind.persistent.model.SingleClause;
import com.google.common.collect.Lists;
import org.springframework.cache.annotation.CacheConfig;

import java.util.Collection;
import java.util.List;

/**
 * created by huangkang
 */
@CacheConfig(cacheNames = Info.NAME)
public interface InfoRepository extends AbstractRepositoryBase<Info, String> {
    default List<Info> categoryIds(List<String> categoryIds, String projectId) {
        if (categoryIds == null || categoryIds.size() == 0) {
            return Lists.newArrayList();
        }
        if (categoryIds.size() == 1) {
            return this.query(ClauseBuilder.and()
                    .equal("categoryId", categoryIds.get(0))
                    .equal("projectId",projectId)
                    .toClause()
            );
        }
        return this.query(ClauseBuilder.and()
                .in("categoryId", categoryIds.toArray())
                .equal("projectId",projectId)
                .toClause()
        );
    }
}
