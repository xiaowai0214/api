package com.egova.api.model;

import lombok.Data;

@Data
public class FieldMappingBase {
    private String apiId;

    private String originalJson;

    private String convertRoot;

    private String newJson;

    private Boolean collapse;

}
