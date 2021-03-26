package com.egova.api.cache;

import com.egova.api.entity.Category;
import com.egova.api.facade.ApiCategoryFacade;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author chendb
 * @description:
 * @date 2020-11-07 21:34:10
 */
@Service
@RequiredArgsConstructor
public class ApiCategoryCache {

    protected final Cache<String, List<Category>> cache = CacheBuilder.newBuilder().expireAfterWrite(Duration.ofMinutes(5L)).build();

    private final ApiCategoryFacade categoryFacade;

    public long size() {
        return this.cache.size();
    }


    public List<Category> type(String type) {
        this.ensureCache(type);
        return Optional.ofNullable(cache.getIfPresent(type)).orElse(new ArrayList<>());
    }

    public Category getById(String type,String id) {

        return this.type(type).stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    public Category getByName(String type,String name) {
        return this.type(type).stream().filter(g -> g.getName().equals(name)).findFirst().orElse(null);
    }

    public void ensureCache(String type) {
        if (StringUtils.isEmpty(type)) {
            return;
        }
        List<Category> categories = cache.getIfPresent(type);
        if (categories == null) {
            synchronized (cache) {
                categories = categoryFacade.getListByType(type);
                cache.put(type, categories);
            }
        }
    }

    public void invalidateAll() {
        this.cache.invalidateAll();
    }

    public void invalidate(String... types) {
        if (types == null || types.length == 0) {
            return;
        }
        for (String type : types) {
            if (StringUtils.isNotEmpty(type)) {
                this.cache.invalidate(type);
            }
        }
    }

    public static void clear(String... ids) {
        if (ids == null && ids.length == 0) {
            com.flagwind.application.Application.resolve(ApiCategoryCache.class).invalidateAll();
        } else {
            com.flagwind.application.Application.resolve(ApiCategoryCache.class).invalidate(ids);
        }
    }

}
