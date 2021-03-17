package com.egova.api.service.impl;

import com.egova.api.condition.InfoCondition;
import com.egova.api.domain.*;
import com.egova.api.entity.Info;
import com.egova.api.entity.RequestParam;
import com.egova.api.enums.RequestBodyType;
import com.egova.api.enums.RequestParamType;
import com.egova.api.enums.RequestScope;
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
    private final BaseMicroServiceWrapper baseMicroServiceWrapper;

    @Override
    protected AbstractRepositoryBase<Info, String> getRepository() {
        return infoRepository;
    }

    @Override
    public PageResult<Info> page(QueryModel<InfoCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

    @Override
    public String insert(Info entity) {
        entity.setCreator(UserContext.username());
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return super.insert(entity);
    }

    @Override
    public void update(Info entity) {
        super.update(entity);
    }

    @Override
    public void modify(String id, HashMap<String, Object> map) {
        super.modify(map, SingleClause.equal("id",id));
    }

    @Override
    public List<Category> tree(String categoryId,String projectId) {
        List<String> ids = Lists.newArrayList();
        List<Category> tree = Optional.ofNullable(StringUtils.isBlank(categoryId) ? null : categoryId)
                .map(g -> baseMicroServiceWrapper.getCategoryTreeById(g, ids))
                .orElse(baseMicroServiceWrapper.getCategoryTreeByType(CATEGORY_TYPE, ids));


        Map<String, List<Info>> group = infoRepository
                .categoryIds(ids,projectId).stream()
                .collect(Collectors.groupingBy(Info::getCategoryId));

        tree.forEach(category -> baseMicroServiceWrapper.setCategoryNodeData("apis", category, group));
        return tree;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(ApiInfoModel apiInfoModel) {
        Optional.ofNullable(apiInfoModel.getInfo().getId()).orElseThrow(()-> ExceptionUtils.api("更新时apiId不能为空"));
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
    }

    @Override
    public ApiInfoModel getApiInfoModel(String id) {
        ApiInfoModel apiInfoModel = new ApiInfoModel();
        Info info = Optional.ofNullable(infoRepository.getById(id)).orElseThrow(() -> ExceptionUtils.api("api信息为空"));
        apiInfoModel.setInfo(info);
        Optional.ofNullable(requestHeaderRepository.query(SingleClause.equal("apiId",id))).ifPresent(headers-> apiInfoModel.setRequestHeaders(headers));
        Optional.ofNullable(requestParamRepository.query(SingleClause.equal("apiId",id))).ifPresent(headers-> apiInfoModel.setRequestParams(headers));
        Optional.ofNullable(eventScriptRepository.query(SingleClause.equal("apiId",id))).ifPresent(headers-> apiInfoModel.setEventScripts(headers));
        Optional.ofNullable(authenticationRepository.single("apiId",id)).ifPresent(authentication-> apiInfoModel.setAuthentication(authentication));
        if (info.getRequestBodyType() == RequestBodyType.Json){
            //包装为json
            String json = "";
            Map<String,Object> map = new HashMap<>();
            requestParamRepository.query(SingleClause.equal("apiId",id))
                    .stream()
                    .filter(p-> p.getType() == RequestParamType.Json)
                    .forEach(requestParam -> {
                        map.put(requestParam.getName(),requestParam.getValueContent());
                    });
            json = JsonPathUtils.warpJson(map);
            apiInfoModel.setJson(json);
        }
        return apiInfoModel;
    }

    private void saveAuthentication(ApiInfoModel apiInfoModel) {
        authenticationRepository.delete(SingleClause.equal("projectId", apiInfoModel.getInfo().getProjectId()));
        if (apiInfoModel.getAuthentication() != null ){
            apiInfoModel.getAuthentication().setProjectId(apiInfoModel.getInfo().getProjectId());
            apiInfoModel.getAuthentication().setId(UUID.randomUUID().toString());
            authenticationRepository.insert(apiInfoModel.getAuthentication());
        }
    }

    private void saveScripts(ApiInfoModel apiInfoModel) {
        eventScriptRepository.delete(SingleClause.equal("apiId", apiInfoModel.getInfo().getId()));
        if (!CollectionUtils.isEmpty(apiInfoModel.getEventScripts())){
            apiInfoModel.getEventScripts().forEach(p-> {
                p.setApiId(apiInfoModel.getInfo().getId());
                p.setId(UUID.randomUUID().toString());
            });
            eventScriptRepository.insertList(apiInfoModel.getEventScripts());
        }
    }

    private void saveHeaders(ApiInfoModel apiInfoModel) {
        requestHeaderRepository.delete(ClauseBuilder.and()
                .equal("belongId", apiInfoModel.getInfo().getId())
                .equal("scope", RequestScope.Api)
                .toClause()
        );
        if (!CollectionUtils.isEmpty(apiInfoModel.getRequestHeaders())){
            apiInfoModel.getRequestHeaders().forEach(p-> {
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
        switch (info.getMethod()){
            case GET:
            case DELETE:
                //GET 默认只有queryString 参数
                saveQueryStringParams(apiInfoModel);
                break;
            case POST:
            case PUT:
                saveQueryStringParams(apiInfoModel);
                if (info.getRequestBodyType() == RequestBodyType.Form){
                    saveFormDataParams(apiInfoModel);
                }else if (info.getRequestBodyType() == RequestBodyType.Json){
                    saveJsonParams(apiInfoModel);
                }
                break;
        }
    }

    private void saveQueryStringParams(ApiInfoModel apiInfoModel){
        List<RequestParam> qureyStrings = apiInfoModel.getRequestParams()
                .stream().filter(requestParam -> requestParam.getType() == RequestParamType.QueryString)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(qureyStrings)){
            qureyStrings
                    .forEach(p-> {
                        p.setApiId(apiInfoModel.getInfo().getId());
                        p.setId(UUID.randomUUID().toString());
                    });
            requestParamRepository.insertList(qureyStrings);
        }
    }

    private void saveFormDataParams(ApiInfoModel apiInfoModel){
        List<RequestParam> formParams = apiInfoModel.getRequestParams()
                .stream().filter(requestParam -> requestParam.getType() == RequestParamType.FormData)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(formParams)){
            formParams.forEach(p-> {
                        p.setApiId(apiInfoModel.getInfo().getId());
                        p.setId(UUID.randomUUID().toString());
                    });
            requestParamRepository.insertList(formParams);
        }
    }

    private void saveJsonParams(ApiInfoModel apiInfoModel){
        if (!StringUtils.isEmpty(apiInfoModel.getJson())){
            List<RequestParam> jsonParams = new ArrayList<>();
            String json = apiInfoModel.getJson();
            List<String> jsonPaths = JsonPathUtils.getListJsonPathByJsonString(json);
            jsonPaths.forEach(path->{
                RequestParam requestParam = new RequestParam();
                requestParam.setId(UUID.randomUUID().toString());
                requestParam.setType(RequestParamType.Json);
                requestParam.setApiId(apiInfoModel.getInfo().getId());
                requestParam.setName(path);
                requestParam.setText(path);
                requestParam.setValueContent(JsonPathUtils.readjson(json,path));
                jsonParams.add(requestParam);
            });
            requestParamRepository.insertList(jsonParams);
        }
    }
}
