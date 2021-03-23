package com.egova.api.service.impl;

import com.egova.api.condition.FieldMappingCondition;
import com.egova.api.domain.ConvertConfigRepository;
import com.egova.api.domain.FieldMappingRepository;
import com.egova.api.entity.ConvertConfig;
import com.egova.api.entity.FieldMapping;
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
    public List<FieldMapping> parse(String json, String root, Boolean collapse) {
        List<FieldMapping> fieldMappings = new ArrayList<>();
        List<String> paths = JsonPathUtils.getListJsonPathByJsonString(json);
        if (BooleanUtils.isTrue(collapse)){
            paths = JsonPathUtils.distinctPath(paths);
        }
        paths.stream()
                .filter(path-> StringUtils.isEmpty(root) || path.startsWith(root))
                .forEach(path->{
                    FieldMapping fieldMapping = new FieldMapping();
                    fieldMapping.setOriginalParamPath(path);
                    fieldMapping.setParamPath(path);
                    Object value = JsonPathUtils.readjson(json, path);
                    fieldMapping.setName(StringUtils.isEmpty(root) ? path : path.substring(root.length() + 1));
                    fieldMapping.setValueContent(null == value ? null : value.toString());
                    fieldMappings.add(fieldMapping);
                });
        return fieldMappings;
    }

    @Override
    public FieldMappingModel parseModel(String json, String root, Boolean collapse) {
        FieldMappingModel model = new FieldMappingModel();
        model.setOriginalJson(json);
        model.setConvertRoot(root);
        List<FieldMapping> fieldMappings = parse(json, root,collapse);
        model.setFieldMappings(fieldMappings);
        Map<String,Object> map = new HashMap<>();
        fieldMappings
                .forEach(fieldMapping -> map.put(fieldMapping.getName(),fieldMapping.getValueContent()));
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
