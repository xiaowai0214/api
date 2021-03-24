package com.egova.api.util;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.lang.reflect.Array;
import java.util.*;

public class JSONPathEnhanced extends com.alibaba.fastjson.JSONPath {

    public JSONPathEnhanced(String json) {
        super(json);
    }


    public JSONPathEnhanced(String json, SerializeConfig serializeConfig, ParserConfig parserConfig) {
        super(json, serializeConfig, parserConfig);
    }


    public static Map<String, Object> paths(Object javaObject) {
        return paths(javaObject, SerializeConfig.globalInstance);
    }

    public static Map<String, Object> paths(Object javaObject, SerializeConfig config) {
        Map<Object, String> values = new IdentityHashMap();
        Map<String, Object> paths = new HashMap();
        paths(values, paths, "/", javaObject, config);
        return paths;
    }

    private static void paths(Map<Object, String> values, Map<String, Object> paths, String parent, Object javaObject, SerializeConfig config) {
        if (javaObject != null) {
            String p = (String)values.put(javaObject, parent);
            if (p != null) {
                boolean basicType = javaObject instanceof String || javaObject instanceof Number || javaObject instanceof Date || javaObject instanceof UUID;
                if (!basicType) {
                    return;
                }
            }

            paths.put(parent, javaObject);
            if (javaObject instanceof Map) {
                Map map = (Map)javaObject;
                Iterator var19 = map.entrySet().iterator();

                while(var19.hasNext()) {
                    Object entryObj = var19.next();
                    Map.Entry entry = (Map.Entry)entryObj;
                    Object key = entry.getKey();
                    if (key instanceof String) {
                        String path = parent.equals("/") ? "/" + key : parent + "/" + key;
                        paths(values, paths, path, entry.getValue(), config);
                    }
                }

            } else {
                int len;
                Object item;
                String path;
                if (javaObject instanceof Collection) {
                    Collection collection = (Collection)javaObject;
                    len = 0;

                    for(Iterator var21 = collection.iterator(); var21.hasNext(); ++len) {
                        item = var21.next();
                        path = parent.equals("/") ? "/" + len : parent + "/" + len;
                        paths(values, paths, path, item, config);
                    }

                } else {
                    Class<?> clazz = javaObject.getClass();
                    if (clazz.isArray()) {
                        len = Array.getLength(javaObject);

                        for(int i = 0; i < len; ++i) {
                            item = Array.get(javaObject, i);
                            path = parent.equals("/") ? "/" + i : parent + "/" + i;
                            paths(values, paths, path, item, config);
                        }

                    } else if (!ParserConfig.isPrimitive2(clazz) && !clazz.isEnum()) {
                        ObjectSerializer serializer = config.getObjectWriter(clazz);
                        if (serializer instanceof JavaBeanSerializer) {
                            JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)serializer;

                            try {
                                Map<String, Object> fieldValues = javaBeanSerializer.getFieldValuesMap(javaObject);
                                Iterator var10 = fieldValues.entrySet().iterator();

                                while(var10.hasNext()) {
                                    Map.Entry<String, Object> entry = (Map.Entry)var10.next();
                                    String key = (String)entry.getKey();
                                    if (key instanceof String) {
                                        String pp = parent.equals("/") ? "/" + key : parent + "/" + key;
                                        paths(values, paths, pp, entry.getValue(), config);
                                    }
                                }

                            } catch (Exception var14) {
                                throw new JSONException("toJSON error", var14);
                            }
                        }
                    }
                }
            }
        }else {
            paths.put(parent, javaObject);
        }
    }
}
