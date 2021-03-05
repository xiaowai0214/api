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

    private List<RequestParam> requestParams;

    private List<RequestHeader> requestHeaders;

    private List<EventScript> eventScripts;

    private Authentication authentication;
}
