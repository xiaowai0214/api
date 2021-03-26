package com.egova.api.domain;

import com.egova.api.entity.Category;
import com.egova.data.service.AbstractRepositoryBase;
import com.egova.persistent.Sequence;
import com.egova.persistent.SequenceProvider;
import com.egova.persistent.Sequences;
import com.flagwind.persistent.model.ClauseCombine;
import com.flagwind.persistent.model.ClauseOperator;
import com.flagwind.persistent.model.CombineClause;
import com.flagwind.persistent.model.SingleClause;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author liup
 * 通用树形分类信息
 * @date 2020年02月21日 上午9:21:42
 */
@CacheConfig(cacheNames = Category.NAME)
public interface CategoryRepository extends AbstractRepositoryBase<Category, String>, SequenceProvider {


    default Sequence getSequence() {
        return Sequences.of(Category.class, 3);
    }


    default boolean unique(String name, String type, String id) {
        CombineClause clauses = new CombineClause(ClauseCombine.And);
        clauses.add(new SingleClause("name", name));
        clauses.add(new SingleClause("type", type));
        if (!StringUtils.isBlank(id)) {
            clauses.add(new SingleClause("Id", ClauseOperator.NotEqual, id));
        }
        return this.count(clauses) <= 0;
    }

    @Cacheable
    default List<Category> type(String type) {
        return this.query(SingleClause.equal("type", type), null, null);
    }
}
