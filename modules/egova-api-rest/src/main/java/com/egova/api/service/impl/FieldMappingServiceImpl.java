package com.egova.api.service.impl;

import com.egova.api.condition.FieldMappingCondition;
import com.egova.api.domain.ConvertConfigRepository;
import com.egova.api.domain.FieldMappingRepository;
import com.egova.api.entity.ConvertConfig;
import com.egova.api.entity.FieldMapping;
import com.egova.api.model.FieldMappingBase;
import com.egova.api.model.FieldMappingModel;
import com.egova.api.service.FieldMappingService;
import com.egova.api.util.JsonPathUtils;
import com.egova.data.service.AbstractRepositoryBase;
import com.egova.data.service.TemplateService;
import com.egova.model.PageResult;
import com.egova.model.QueryModel;
import com.flagwind.commons.StringUtils;
import com.flagwind.persistent.model.SingleClause;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Priority;
import java.util.*;

/**
 * created by huangkang
 */
@Slf4j
@Service
@Priority(5)
@RequiredArgsConstructor
public class FieldMappingServiceImpl extends TemplateService<FieldMapping, String> implements FieldMappingService {

    private final FieldMappingRepository fieldMappingRepository;
    private final ConvertConfigRepository convertConfigRepository;

    @Override
    protected AbstractRepositoryBase<FieldMapping, String> getRepository() {
        return fieldMappingRepository;
    }

    @Override
    public PageResult<FieldMapping> page(QueryModel<FieldMappingCondition> model) {
        return super.page(model.getCondition(), model.getPaging(), model.getSorts());
    }

    @Override
    public List<FieldMapping> apiId(String apiId) {
        return fieldMappingRepository.many("apiId",apiId);
    }

    @Override
    public List<FieldMapping> parse(FieldMappingBase fieldMappingBase) {
        List<FieldMapping> fieldMappings = new ArrayList<>();
        List<String> paths = JsonPathUtils.getListJsonPathByJsonString(fieldMappingBase.getOriginalJson());
        if (BooleanUtils.isTrue(fieldMappingBase.getCollapse())){
            paths = JsonPathUtils.distinctPath(paths);
        }
        paths.stream()
                .filter(path-> StringUtils.isEmpty(fieldMappingBase.getConvertRoot()) || path.startsWith(fieldMappingBase.getConvertRoot()))
//                .map(path->{
//                    String convertPath = path;
//                    if (StringUtils.isNotBlank(fieldMappingBase.getConvertRoot()) && path.startsWith(fieldMappingBase.getConvertRoot())){
//                        if (path.startsWith(fieldMappingBase.getConvertRoot() + "[*].")){
//                            convertPath = "data[*]." + path.substring(fieldMappingBase.getConvertRoot().length() + 4);
//                        }else if (path.startsWith(fieldMappingBase.getConvertRoot() + ".")){
//                            convertPath = "data[0]." + path.substring(fieldMappingBase.getConvertRoot().length() + 1);
//                        }
//                    }
//                    return convertPath;
//                })
                .forEach(path->{
                    FieldMapping fieldMapping = new FieldMapping();
                    fieldMapping.setOriginalParamPath(path);
                    fieldMapping.setParamPath(path);
                    Object value = JsonPathUtils.readjson(fieldMappingBase.getOriginalJson(), path);

                    String convertPath = path;
                    if (StringUtils.isNotBlank(fieldMappingBase.getConvertRoot()) && path.startsWith(fieldMappingBase.getConvertRoot())){
                        if (path.startsWith(fieldMappingBase.getConvertRoot() + "[*].")){
                            convertPath = "data[*]." + path.substring(fieldMappingBase.getConvertRoot().length() + 4);
                        }else if (path.startsWith(fieldMappingBase.getConvertRoot() + ".")){
                            convertPath = "data[0]." + path.substring(fieldMappingBase.getConvertRoot().length() + 1);
                        }
                    }
                    fieldMapping.setName(convertPath);

//                    fieldMapping.setName(path);
                    fieldMapping.setValueContent(null == value ? null : value.toString());
                    fieldMappings.add(fieldMapping);
                });
        return fieldMappings;
    }

    @Override
    public FieldMappingModel parseModel(FieldMappingBase fieldMappingBase) {
        FieldMappingModel model = new FieldMappingModel();
        model.setOriginalJson(fieldMappingBase.getOriginalJson());
        model.setConvertRoot(fieldMappingBase.getConvertRoot());
        model.setCollapse(fieldMappingBase.getCollapse());
        List<FieldMapping> fieldMappings = parse(fieldMappingBase);
        model.setFieldMappings(fieldMappings);
        Map<String,Object> map = new HashMap<>();
//        fieldMappings
//                .forEach(fieldMapping -> map.put(fieldMapping.getName(),fieldMapping.getValueContent()));

        fieldMappings.stream()
                .filter(p -> !StringUtils.isEmpty(p.getName()))
                .forEach(fieldMapping -> {
                    Object value = JsonPathUtils.readjson(fieldMappingBase.getOriginalJson(),fieldMapping.getParamPath());
                    if (fieldMapping.getName().contains("*") && value instanceof net.minidev.json.JSONArray){
                        net.minidev.json.JSONArray jsonArray = (net.minidev.json.JSONArray)value;
                        for (int i = 0; i < jsonArray.size(); i++) {
                            String item = fieldMapping.getName().replaceAll("[*]",String.valueOf(i));
                            value = jsonArray.get(i);
                            Object transform = JsonPathUtils.transform(value, fieldMapping.getValueType());
                            map.put(item,transform);
                        }
                    }else {
                        Object transform = JsonPathUtils.transform(value, fieldMapping.getValueType());
                        map.put(fieldMapping.getName(),transform);
                    }


                });

        String newJson = JsonPathUtils.warpJson(map);

        model.setNewJson(newJson);
        return model;
    }

    @Override
    public void insert(FieldMappingModel model) {
        fieldMappingRepository.delete(SingleClause.equal("apiId",model.getApiId()));
        convertConfigRepository.delete(SingleClause.equal("apiId",model.getApiId()));
        ConvertConfig convertConfig = model.getConvertConfig();
        if (convertConfig != null){
            convertConfig.setApiId(model.getApiId());
            convertConfig.setId(UUID.randomUUID().toString());
            convertConfigRepository.insert(convertConfig);
        }
        model.getFieldMappings()
                .forEach(fieldMapping -> {
                    fieldMapping.setId(UUID.randomUUID().toString());
                    fieldMapping.setApiId(model.getApiId());
                });
        if (!CollectionUtils.isEmpty(model.getFieldMappings())){
            fieldMappingRepository.insertList(model.getFieldMappings());
        }
    }

    @Override
    public FieldMappingModel getModelByApiId(String apiId) {
        FieldMappingModel model = new FieldMappingModel();
        model.setApiId(apiId);
        model.setFieldMappings(fieldMappingRepository.query(SingleClause.equal("apiId",apiId)));
        model.setConvertConfig(convertConfigRepository.single("apiId",apiId));
        return model;
    }
}
