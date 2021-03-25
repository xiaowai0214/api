package com.egova.api.service.impl;

import com.egova.api.condition.InfoCondition;
import com.egova.api.domain.*;
import com.egova.api.entity.*;
import com.egova.api.enums.*;
import com.egova.api.facade.TrendsFacade;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.service.BaseMicroServiceWrapper;
import com.egova.api.service.InfoService;
import com.egova.api.util.JsonPathUtils;
import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.entity.Category;
import com.egova.exception.ExceptionUtils;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.egova.persistent.ClauseBuilder;
import com.egova.security.UserContext;
import com.flagwind.commons.StringUtils;
import com.flagwind.persistent.model.SingleClause;
import com.flagwind.persistent.model.Sorting;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Priority;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * created by huangkang
 */
@Slf4j
@Service
@Priority(5)
@RequiredArgsConstructor
public class InfoServiceImpl extends TemplateService<Info, String> implements InfoService {
    private static final String CATEGORY_TYPE = "api";

    private final InfoRepository infoRepository;
    private final AuthenticationRepository authenticationRepository;
    private final EventScriptRepository eventScriptRepository;
    private final RequestHeaderRepository requestHeaderRepository;
    private final RequestParamRepository requestParamRepository;
    private final FieldMappingRepository fieldMappingRepository;
    private final ConvertConfigRepository convertConfigRepository;
    private final BaseMicroServiceWrapper baseMicroServiceWrapper;
    private final TrendsFacade trendsFacade;

    @Override
    protected AbstractRepositoryBase<Info, String> getRepository() {
        return infoRepository;
    }

    @Override
    public PageResult<Info> page(QueryModel<InfoCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

    @Override
    public List<Info> list(InfoCondition condition) {
        return query(condition, Sorting.descending("createTime"));
    }

    @Override
    public String insert(Info entity) {
        entity.setCreator(UserContext.username());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String insert = super.insert(entity);
        trendsFacade.insert(new Trends(entity.getProjectId(), entity.getCategoryId(), insert, entity.getName(), TrendsType.APi, OperateType.Insert));
        return insert;
    }

    @Override
    public void update(Info entity) {
        super.update(entity);
        trendsFacade.insert(new Trends(entity.getProjectId(), entity.getCategoryId(), entity.getId(), entity.getName(), TrendsType.APi, OperateType.Update));
    }

    @Override
    public void modify(String id, HashMap<String, Object> map) {
        super.modify(map, SingleClause.equal("id", id));
        Info entity = infoRepository.getById(id);
        trendsFacade.insert(new Trends(entity.getProjectId(), entity.getCategoryId(), entity.getId(), entity.getName(), TrendsType.APi, OperateType.Update));
    }

    @Override
    public int deleteById(String id) {
        Info entity = infoRepository.getById(id);
        super.deleteById(id);
        trendsFacade.insert(new Trends(entity.getProjectId(), entity.getCategoryId(), entity.getId(), entity.getName(), TrendsType.APi, OperateType.Delete));
        return 1;
    }

    @Override
    public List<Category> tree(String categoryId, String projectId) {
        List<String> ids = Lists.newArrayList();
        List<Category> tree = Optional.ofNullable(StringUtils.isBlank(categoryId) ? null : categoryId)
                .map(g -> baseMicroServiceWrapper.getCategoryTreeById(g, ids))
                .orElse(baseMicroServiceWrapper.getCategoryTreeByType(CATEGORY_TYPE, ids));


        Map<String, List<Info>> group = infoRepository
                .categoryIds(ids, projectId).stream()
                .collect(Collectors.groupingBy(Info::getCategoryId));

        tree.forEach(category -> baseMicroServiceWrapper.setCategoryNodeData("apis", category, group));
        return tree;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(ApiInfoModel apiInfoModel) {
        Optional.ofNullable(apiInfoModel.getInfo().getId()).orElseThrow(() -> ExceptionUtils.api("更新时apiId不能为空"));
        //0. api
        infoRepository.update(apiInfoModel.getInfo());

        //1. 参数
        saveParams(apiInfoModel);

        //2. Header
        saveHeaders(apiInfoModel);

        //3.脚本
        saveScripts(apiInfoModel);

        //4.认证
        saveAuthentication(apiInfoModel);

        //5.转换规则
        saveConvertConfig(apiInfoModel);

        //6.转换字段映射
        saveFieldMapping(apiInfoModel);

        trendsFacade.insert(new Trends(apiInfoModel.getInfo().getProjectId(), apiInfoModel.getInfo().getCategoryId(), apiInfoModel.getInfo().getId(), apiInfoModel.getInfo().getName(), TrendsType.APi, OperateType.Update));
    }

    private void saveConvertConfig(ApiInfoModel model) {
        convertConfigRepository.delete(SingleClause.equal("apiId", model.getInfo().getId()));
        ConvertConfig convertConfig = model.getConvertConfig();
        if (convertConfig != null) {
            convertConfig.setApiId(model.getInfo().getId());
            convertConfig.setId(UUID.randomUUID().toString());
            convertConfigRepository.insert(convertConfig);
        }
    }

    private void saveFieldMapping(ApiInfoModel model) {
        fieldMappingRepository.delete(SingleClause.equal("apiId", model.getInfo().getId()));
        model.getFieldMappings()
                .forEach(fieldMapping -> {
                    fieldMapping.setId(UUID.randomUUID().toString());
                    fieldMapping.setApiId(model.getInfo().getId());
                });
        if (!CollectionUtils.isEmpty(model.getFieldMappings())) {
            fieldMappingRepository.insertList(model.getFieldMappings());
        }
    }

    @Override
    public ApiInfoModel getApiInfoModel(String id) {
        ApiInfoModel apiInfoModel = new ApiInfoModel();
        Info info = Optional.ofNullable(infoRepository.getById(id)).orElseThrow(() -> ExceptionUtils.api("api信息为空"));
        apiInfoModel.setInfo(info);
        List<RequestParam> requestParams = requestParamRepository.query(SingleClause.equal("apiId", id));
        List<EventScript> eventScripts = eventScriptRepository.query(SingleClause.equal("apiId", id));

        Optional.ofNullable(requestHeaderRepository.query(SingleClause.equal("belongId", id))).ifPresent(headers -> apiInfoModel.setRequestHeaders(headers));
        Optional.ofNullable(requestParams).ifPresent(params -> {
            apiInfoModel.setQueryParams(params.stream().filter(p-> p.getType() == RequestParamType.QueryString).collect(Collectors.toList()));
            apiInfoModel.setFormParams(params.stream().filter(p-> p.getType() == RequestParamType.FormData).collect(Collectors.toList()));
        });
        Optional.ofNullable(eventScripts).ifPresent(scripts -> {
            apiInfoModel.setPreScripts(scripts.stream().filter(p-> p.getEventType() == EventType.Previous).collect(Collectors.toList()));
            apiInfoModel.setPostScripts(scripts.stream().filter(p-> p.getEventType() == EventType.Post).collect(Collectors.toList()));
        });
        Optional.ofNullable(authenticationRepository.single("projectId", info.getProjectId())).ifPresent(authentication -> apiInfoModel.setAuthentication(authentication));
        Optional.ofNullable(convertConfigRepository.single("apiId", id)).ifPresent(convertConfig -> apiInfoModel.setConvertConfig(convertConfig));
        Optional.ofNullable(fieldMappingRepository.query(SingleClause.equal("apiId", id))).ifPresent(fieldMappings -> apiInfoModel.setFieldMappings(fieldMappings));
        if (info.getRequestBodyType() == RequestBodyType.Json) {
            //包装为json
            String json = "";
            Map<String, Object> map = new HashMap<>();
            requestParamRepository.query(SingleClause.equal("apiId", id))
                    .stream()
                    .filter(p -> p.getType() == RequestParamType.Json)
                    .forEach(requestParam -> {
                        map.put(requestParam.getName(), requestParam.getValueContent());
                    });
            json = JsonPathUtils.warpJson(map);
            apiInfoModel.setJson(json);
        }
        return apiInfoModel;
    }

    private void saveAuthentication(ApiInfoModel apiInfoModel) {
        authenticationRepository.delete(SingleClause.equal("projectId", apiInfoModel.getInfo().getProjectId()));
        if (apiInfoModel.getAuthentication() != null) {
            apiInfoModel.getAuthentication().setProjectId(apiInfoModel.getInfo().getProjectId());
            apiInfoModel.getAuthentication().setId(UUID.randomUUID().toString());
            authenticationRepository.insert(apiInfoModel.getAuthentication());
        }
    }

    private void saveScripts(ApiInfoModel apiInfoModel) {
        List<EventScript> scripts = new ArrayList<>();
        if (CollectionUtils.isEmpty(apiInfoModel.getPreScripts())){
            scripts.addAll(apiInfoModel.getPreScripts());
        }
        if (CollectionUtils.isEmpty(apiInfoModel.getPostScripts())){
            scripts.addAll(apiInfoModel.getPostScripts());
        }
        eventScriptRepository.delete(SingleClause.equal("apiId", apiInfoModel.getInfo().getId()));
        if (!CollectionUtils.isEmpty(scripts)) {
            scripts.forEach(p -> {
                p.setApiId(apiInfoModel.getInfo().getId());
                p.setId(UUID.randomUUID().toString());
            });
            eventScriptRepository.insertList(scripts);
        }
    }

    private void saveHeaders(ApiInfoModel apiInfoModel) {
        requestHeaderRepository.delete(ClauseBuilder.and()
                .equal("belongId", apiInfoModel.getInfo().getId())
                .equal("scope", RequestScope.Api)
                .toClause()
        );
        if (!CollectionUtils.isEmpty(apiInfoModel.getRequestHeaders())) {
            apiInfoModel.getRequestHeaders().forEach(p -> {
                p.setBelongId(apiInfoModel.getInfo().getId());
                p.setScope(RequestScope.Api);
                p.setId(UUID.randomUUID().toString());
            });
            requestHeaderRepository.insertList(apiInfoModel.getRequestHeaders());
        }
    }

    private void saveParams(ApiInfoModel apiInfoModel) {
        requestParamRepository.delete(ClauseBuilder.and()
                .equal("apiId", apiInfoModel.getInfo().getId())
                .toClause()
        );
        Info info = apiInfoModel.getInfo();
        switch (info.getMethod()) {
            case GET:
            case DELETE:
                //GET 默认只有queryString 参数
                saveQueryStringParams(apiInfoModel);
                break;
            case POST:
            case PUT:
                saveQueryStringParams(apiInfoModel);
                if (info.getRequestBodyType() == RequestBodyType.Form) {
                    saveFormDataParams(apiInfoModel);
                } else if (info.getRequestBodyType() == RequestBodyType.Json) {
                    saveJsonParams(apiInfoModel);
                }
                break;
        }
    }

    private void saveQueryStringParams(ApiInfoModel apiInfoModel) {
        List<RequestParam> qureyStrings = apiInfoModel.getQueryParams()
                .stream().filter(requestParam -> requestParam.getType() == RequestParamType.QueryString)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(qureyStrings)) {
            qureyStrings
                    .forEach(p -> {
                        p.setApiId(apiInfoModel.getInfo().getId());
                        p.setId(UUID.randomUUID().toString());
                    });
            requestParamRepository.insertList(qureyStrings);
        }
    }

    private void saveFormDataParams(ApiInfoModel apiInfoModel) {
        List<RequestParam> formParams = apiInfoModel.getFormParams()
                .stream().filter(requestParam -> requestParam.getType() == RequestParamType.FormData)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(formParams)) {
            formParams.forEach(p -> {
                p.setApiId(apiInfoModel.getInfo().getId());
                p.setId(UUID.randomUUID().toString());
            });
            requestParamRepository.insertList(formParams);
        }
    }

    private void saveJsonParams(ApiInfoModel apiInfoModel) {
        if (!StringUtils.isEmpty(apiInfoModel.getJson())) {
            List<RequestParam> jsonParams = new ArrayList<>();
            String json = apiInfoModel.getJson();
            List<String> jsonPaths = JsonPathUtils.getListJsonPathByJsonString(json);
            jsonPaths.forEach(path -> {
                RequestParam requestParam = new RequestParam();
                requestParam.setId(UUID.randomUUID().toString());
                requestParam.setType(RequestParamType.Json);
                requestParam.setApiId(apiInfoModel.getInfo().getId());
                requestParam.setName(path);
                requestParam.setText(path);
                Object readjson = JsonPathUtils.readjson(json, path);
                requestParam.setValueContent(null == readjson ? null : readjson.toString());
                jsonParams.add(requestParam);
            });
            requestParamRepository.insertList(jsonParams);
        }
    }
}
