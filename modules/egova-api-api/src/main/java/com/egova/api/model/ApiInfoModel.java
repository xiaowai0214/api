package com.egova.api.model;

import com.egova.api.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class ApiInfoModel {

    private Info info;

    /*
     * json格式参数
    */
    private String json;

    /*
     * form参数
     */
    private List<RequestParam> formParams;

    /*
     * queryString参数
     */
    private List<RequestParam> queryParams;

    private List<RequestHeader> requestHeaders;

    /*
     * 前置脚本
     */
    private List<EventScript> preScripts;

    /*
     * 后置脚本
     */
    private List<EventScript> postScripts;

    private Authentication authentication;

    private List<FieldMapping> fieldMappings;

    private ConvertConfig convertConfig;
}
