package com.egova.form;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.egova.api.util.JsonPathUtils;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.util.List;

public class JsonPathTest {
    //    测试方法
    @Test
    public void jsonReplaceVal(){
        //构造json
        JSONObject jsonObject = new JSONObject();
        JSONPath.set(jsonObject,"data.person","个人");
        JSONPath.set(jsonObject,"data.student[0].age","20");
        JSONPath.set(jsonObject,"data.student[0].name[0].v","张三");
        JSONPath.set(jsonObject,"data.student[1].age","20");
        JSONPath.set(jsonObject,"data.student[1].name[1].nn","张三");
        JSONPath.set(jsonObject,"data.student[1].name[0].nn","张");
        //获取路径
        List<String> listjsonPath= JsonPathUtils.getListJsonPath(jsonObject);
        System.out.println("listJsonPath:"+listjsonPath.toString());
        //将路径上的内容替换
        for(int i=0;i<listjsonPath.size();i++){
            String  tempPath=  listjsonPath.get(i);
            String[] fields=tempPath.split("\\.");
            if (fields.length>0) {
                String lastFields = fields[fields.length - 1];
                DocumentContext ext = JsonPath.parse(jsonObject);
                if (lastFields.equals("nn")) {
                    JsonPath p = JsonPath.compile("$." + tempPath);
                    //可先取值在对值进行处理
                    ext.set(p, "吕厚帅");
                    String NewList = ext.jsonString();
                    System.out.println("NewsList:" + NewList);
                }
            }

        }
    }
}
