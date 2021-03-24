package com.egova.api.model;

import com.egova.api.entity.ConvertConfig;
import com.egova.api.entity.FieldMapping;
import lombok.Data;

import java.util.List;

@Data
public class FieldMappingModel extends FieldMappingBase{

    private List<FieldMapping> fieldMappings;

    private ConvertConfig convertConfig;

}
