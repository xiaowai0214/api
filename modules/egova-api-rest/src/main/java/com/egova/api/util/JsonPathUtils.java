package com.egova.api.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

import java.util.*;
import java.util.stream.Collectors;

public class JsonPathUtils {
    //获取jsonPath

    public static List<String> getListJsonPathByJsonString(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        return getListJsonPath(jsonObject);
    }

    public static List<String> getListJsonPath(JSONObject jsonObject) {

        List<String> jsonPaths= JSONPath.paths(jsonObject).keySet().stream().collect(Collectors.toList());
        List<String> parentNode=new ArrayList<>();
        //根节点
        parentNode.add("/");
        for(int i=1;i<jsonPaths.size();i++){
            if (jsonPaths.get(i).lastIndexOf("/")>0){

                parentNode.add(jsonPaths.get(i).substring(0,jsonPaths.get(i).lastIndexOf("/")));

            }
        }
        //删除父节点的key
        for(String p:parentNode){
            jsonPaths.remove(p);
        }
        List<String> jsonPathList=new ArrayList<>();
        Iterator<String> jsonpath=jsonPaths.iterator();
        //将/t替换成.
        while (jsonpath.hasNext()){
            String  tempjsonPath=jsonpath.next();
            for(int j=tempjsonPath.length();--j>=0;){

                if(Character.isDigit(tempjsonPath.charAt(j))){
                    //将0.1.2.3.4 替换为[1] [2] [3] [4]
                    tempjsonPath=  tempjsonPath.replaceAll("/"+ tempjsonPath.charAt(j),"["+tempjsonPath.charAt(j)+"]");
                }
            }
            jsonPathList.add(tempjsonPath.replaceFirst("/", "").replaceAll("/", "."));
        }
        return jsonPathList;
    }


//    获取json路径上的值(com.jayway.jsonpath)

    //读取值
    public static String readjson(String json, String jsonPath) {
        try {
            Object value = JsonPath.read(json, jsonPath, new Predicate[0]);
            if (value instanceof Integer) {
                return value.toString();
            } else if (value instanceof String) {
                return value.toString();
            } else if (value instanceof Boolean) {
                return value.toString();
            } else if (value instanceof JSONArray) {
                JSONArray arr = (JSONArray) value;
                if (!arr.isEmpty()) {
                    return arr.toJSONString();
                } else
                    return "";
            } else if (value instanceof LinkedHashMap) {
                return value.toString();
            } else if (value instanceof Float) {
                return value.toString();
            } else {
                return value.toString();
            }
        } catch (Exception e) {
            return "path not found";
        }
    }

    public static String warpJson(Map<String,Object> jsonPathMap){
        //构造json
        JSONObject jsonObject = new JSONObject();
        jsonPathMap.forEach((k,v)->{
            JSONPath.set(jsonObject,k,v);
        });
        return jsonObject.toJSONString();
    }


//    测试方法
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
        List<String> listjsonPath=getListJsonPath(jsonObject);
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

    public static void main(String[] args) {
        String params = "{\n" +
                "    \"username\":\"admin\",\n" +
                "    \"password\":\"123456\",\n" +
                "    \"grant_type\":\"password\",\n" +
                "    \"client_id\":\"unity-client\",\n" +
                "    \"client_secret\":\"unity\",\n" +
                "    \"person\":{\n" +
                "        \"sex\":\"1\",\n" +
                "        \"age\":12,\n" +
                "        \"department\":{\n" +
                "            \"id\":\"123\",\n" +
                "            \"name\":\"智能产品bu\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"roles\":[\n" +
                "        {\n" +
                "            \"id\":\"123\",\n" +
                "            \"name\":\"java\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"234\",\n" +
                "            \"name\":\"打工人\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(params);
        List<String> listJsonPath = getListJsonPath(jsonObject);
        listJsonPath.forEach(path->System.out.println(path));
    }

}