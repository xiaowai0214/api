package com.egova.api.model;

import com.egova.api.entity.FieldMapping;
import lombok.Data;

import java.util.List;

@Data
public class FileldMappingModel {
    private List<FieldMapping> fieldMappings;

    private String origionJson;

    private String convertRoot;

    private String newJson;

}
