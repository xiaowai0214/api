package com.egova.api.service.impl;

import com.egova.api.cache.ApiCategoryCache;
import com.egova.api.condition.AdjustSortCondition;
import com.egova.api.condition.CategoryCondition;
import com.egova.api.domain.CategoryRepository;
import com.egova.api.entity.Category;
import com.egova.api.service.CategoryService;
import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.exception.ExceptionUtils;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.egova.persistent.AggregateDescriptor;
import com.egova.persistent.SequenceGenerator;
import com.flagwind.persistent.AggregateType;
import com.flagwind.persistent.model.SingleClause;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Priority;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Priority(1)
@RequiredArgsConstructor
public class CategoryServiceImpl extends TemplateService<Category, String> implements CategoryService {


    private final CategoryRepository repository;

    private final SequenceGenerator sequenceGenerator;
    protected Cache<String, List<Category>> cache = CacheBuilder.newBuilder().expireAfterAccess(Duration.ofMinutes(5)).build();

    @Override
    protected AbstractRepositoryBase<Category, String> getRepository() {
        return repository;
    }

    private void getTreeIdById(String id, List<String> ids) {
        if (StringUtils.isNotBlank(id)) {
            ids.add(id);
            List<Category> items = getChildrenFromCache(id, null);
            if (items == null || items.isEmpty()) {
                return;
            }
            for (Category c : items) {
                getTreeIdById(c.getId(), ids);
            }
        }
    }

    @Override
    public List<Category> children(String id) {
        List<Category> list = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            return list;
        }
        list = repository.query(SingleClause.equal("parentId", id));
        Collections.sort(list);
        return list;
    }

    @Override
    public int deleteById(String id) {
        return this.deleteByIds(id);
    }

    @Override
    public int deleteByIds(String... ids) {
        List<String> treeIds = new ArrayList<>();
        for (String id : ids) {
            getTreeIdById(id, treeIds);
        }
        return super.deleteByIds(treeIds);
    }

    @Override
    public List<Category> query(CategoryCondition condition) {
        return super.query(condition);
    }

    @Override
    public Long count(CategoryCondition condition) {
        return super.count(condition);
    }

    /**
     * 判断功能代码是否唯一
     */
    @Override
    public Boolean conflict(String name) {

        if (StringUtils.isBlank(name)) {
            return false;
        }
        SingleClause clause = new SingleClause("name", name);
        List<Category> list = getRepository().query(clause);
        return !list.isEmpty();
    }

    @Override
    public List<Category> getByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        String[] array = ids.stream().toArray(String[]::new);
        return query(SingleClause.in("id", array));
    }

    @Override
    public String insert(Category entity) {
//        entity.setId(getKey());
        entity.setId(UUID.randomUUID().toString());
        this.check(entity);
        if (super.count(null) > 0) {
            Integer sort = super.aggregate(AggregateDescriptor.create(AggregateType.Max, "sort"), null);
            entity.setSort(Optional.ofNullable(sort).orElse(0) + 1);
        } else {
            entity.setSort(0);
        }
        return super.insert(entity);
    }

    private void check(Category entity){
        if (StringUtils.isEmpty(entity.getType())) {
            throw ExceptionUtils.api("type不能为空");
        }
        List<Category> list = this.repository.type(entity.getType());
        if (StringUtils.isBlank(entity.getParentId())) {
            // 根目录
            list = list.stream()
                    .filter(e -> StringUtils.isBlank(e.getParentId()))
                    .filter(e -> StringUtils.equals(e.getName(), entity.getName()))
                    .filter(e -> StringUtils.equals(e.getType(), entity.getType()))
                    .filter(e -> !StringUtils.equals(e.getId(), entity.getId()))
                    .collect(Collectors.toList());
        } else {
            list = list.stream()
                    .filter(e -> StringUtils.equals(e.getParentId(), entity.getParentId()))
                    .filter(e -> StringUtils.equals(e.getName(), entity.getName()))
                    .filter(e -> !StringUtils.equals(e.getId(), entity.getId()))
                    .collect(Collectors.toList());
        }
        if (!CollectionUtils.isEmpty(list)) {
            throw ExceptionUtils.api("同一层级下已经存在该目录名称, 无法重命名");
        }
    }

    @Override
    public void update(Category entity) {
        this.check(entity);
        super.update(entity);
    }

    @Override
    public PageResult<Category> page(QueryModel<CategoryCondition> query) {
        return super.page(query.getCondition(), query.getPaging(), query.getSorts());
    }

    @Override
    public List<Category> getTreeById(String id) {
        if (!StringUtils.isEmpty(id)) {
            Category entity = this.getById(id);
            if (entity == null) {
                throw ExceptionUtils.api("找不到指定id的信息");
            }
            entity.set("children", this.getChildrenNode(entity.getId(), entity.getType()));
            return Arrays.asList(entity);

        } else {

            return getChildrenNode(null, null);
        }
    }

    @Override
    public List<Category> getListByType(String type) {
        if (StringUtils.isEmpty(type)) {
            throw ExceptionUtils.api("type不能为空");
        }
        return this.query(SingleClause.equal("type",type));
    }

    @Override
    public List<Category> getTreeByType(String type) {
        if (StringUtils.isEmpty(type)) {
            throw ExceptionUtils.api("type不能为空");
        }
        return this.getChildrenNode(null, type);
    }

    @Override
    public List<Category> getTreeByTypeAndProject(String type, String projectId) {
        if (StringUtils.isEmpty(type)) {
            throw ExceptionUtils.api("type不能为空");
        }
        return this.getChildrenNode(null, type,projectId);
    }

    // region 内部缓存

    /**
     * 调整顺序
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean adjustSort(AdjustSortCondition condition) {
        if (condition.getOriginal() == condition.getTarget()) {
            throw ExceptionUtils.api("调整前后顺序不能相同");
        }
        CategoryCondition categoryCondition = new CategoryCondition();
        categoryCondition.setParentId(condition.getParentId());
        categoryCondition.setType(condition.getType());
        List<Category> originalList;
        if (condition.getTarget() > condition.getOriginal()) {
            categoryCondition.setStartSort(condition.getOriginal());
            categoryCondition.setEndSort(condition.getTarget());
            originalList = this.query(categoryCondition);
            if (!originalList.isEmpty()) {
                for (Category category : originalList) {
                    if (category.getSort() == condition.getOriginal()) {
                        category.setSort(condition.getTarget());
                    } else if (category.getSort() > condition.getOriginal()) {
                        category.setSort(category.getSort() - 1);
                    }
                }
                this.updateList(originalList);
            }
        } else {
            categoryCondition.setStartSort(condition.getTarget());
            categoryCondition.setEndSort(condition.getOriginal());
            originalList = this.query(categoryCondition);
            if (!originalList.isEmpty()) {
                for (Category category : originalList) {
                    if (category.getSort() == condition.getOriginal()) {
                        category.setSort(condition.getTarget());
                    } else if (category.getSort() < condition.getOriginal()) {
                        category.setSort(category.getSort() + 1);
                    }
                }
                this.updateList(originalList);
            }
        }
        return true;
    }

    public List<Category> getDataFromCache(String type) {
        try {
            List<Category> list = cache.get("type:" + Optional.ofNullable(type).orElse("_all"), () -> this.repository.getAll().stream().filter(p -> StringUtils.equals(p.getType(), type)).collect(Collectors.toList()));
            Collections.sort(list);
            return list;
        } catch (Exception e) {
            log.warn("读取 category 的子节点缓层数据异常", e);
            throw ExceptionUtils.api("读取 category 的子节点缓层数据异常", e);
        }
    }

    public List<Category> getDataFromCache(String type,String projectId) {
        try {
            List<Category> list = cache.get("projectId:" + projectId + ":"+ Optional.ofNullable(type).orElse("_all"), () -> this.repository.getAll().stream().filter(p -> StringUtils.equals(p.getType(), type) && com.flagwind.commons.StringUtils.equals(p.getProjectId(),projectId)).collect(Collectors.toList()));
            Collections.sort(list);
            return list;
        } catch (Exception e) {
            log.warn("读取 category 的子节点缓层数据异常", e);
            throw ExceptionUtils.api("读取 category 的子节点缓层数据异常", e);
        }
    }

    public List<Category> getChildrenFromCache(String parentId, String type) {
        List<Category> all = getDataFromCache(type);
        Stream<Category> list = all.stream()
                .filter(g -> StringUtils.equals(Strings.nullToEmpty(g.getParentId()), Strings.nullToEmpty(parentId)));

        return list.collect(Collectors.toList());
    }

    public List<Category> getChildrenFromCache(String parentId, String type, String projectId) {
        List<Category> all = getDataFromCache(type,projectId);
        Stream<Category> list = all.stream()
                .filter(g -> StringUtils.equals(Strings.nullToEmpty(g.getParentId()), Strings.nullToEmpty(parentId)));

        return list.collect(Collectors.toList());
    }


    private List<Category> getChildrenNode(String parentId, String type) {
        List<Category> list = this.getChildrenFromCache(parentId, type);
        list.sort(Comparator.comparing(p-> p.getSort() == null ? Integer.MAX_VALUE : Integer.valueOf(p.getSort())));
        for (Category item : list) {
            item.set("children", this.getChildrenNode(item.getId(), type));
        }
        return list;
    }

    private List<Category> getChildrenNode(String parentId, String type, String projectId) {
        List<Category> list = this.getChildrenFromCache(parentId, type,projectId);
        list.sort(Comparator.comparing(p-> p.getSort() == null ? Integer.MAX_VALUE : Integer.valueOf(p.getSort())));
        for (Category item : list) {
            item.set("children", this.getChildrenNode(item.getId(), type,projectId));
        }
        return list;
    }

    @Override
    public void clearCache() {
        repository.clearCache();
        cache.invalidateAll();
        ApiCategoryCache.clear();
    }


    // endregion

    @Override
    public String getKey() {
        return sequenceGenerator.generate(repository.getSequence());
    }


    @Override
    public Map<String, String> getNameMap() {
        return this.map("id", "name");
    }
}
