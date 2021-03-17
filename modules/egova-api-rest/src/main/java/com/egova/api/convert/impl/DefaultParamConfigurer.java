package com.egova.api.convert.impl;

import com.egova.api.convert.ParamConfigurer;
import com.egova.data.designer.QueryContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
/*
 * @Description : 动态替换参数的环境变量，将环境变量存在context
 */
@Order(1)
@Component
public class DefaultParamConfigurer implements ParamConfigurer {

    @Override
    public void configure(Map<String, Object> map, QueryContext context) {
        //例如 替换username
         context.put("username", "admin");
    }
}
