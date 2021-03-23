package com.egova.api.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiResult {

    private int code;

    private Long time;

    private Long size;

    private String content;

    private String originalContent;

    private Map<String,Object> requestHeaders = new HashMap<>();

    private Map<String,Object> responseHeaders = new HashMap<>();


}
