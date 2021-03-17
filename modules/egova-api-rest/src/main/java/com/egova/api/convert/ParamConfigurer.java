package com.egova.api.convert;

import com.egova.data.designer.QueryContext;

import java.util.Map;

public interface ParamConfigurer {
    void configure(Map<String, Object> map, QueryContext context);
}
