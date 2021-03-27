package com.egova.form;

import com.alibaba.fastjson.JSONObject;
import com.egova.api.entity.RequestParam;
import com.egova.api.enums.DataType;
import com.egova.api.util.JsonPathUtils;
import com.egova.json.utils.JsonUtils;
import com.flagwind.commons.Monment;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class JsonPathTest {
    //    测试方法
    @Test
    public void jsonReplaceVal(){
        //构造json
        JSONObject jsonObject = new JSONObject();
//        JSONPath.set(jsonObject,"data.person","个人");
//        JSONPath.set(jsonObject,"data.student[0].age","20");
//        JSONPath.set(jsonObject,"data.student[0].name[0].v","张三");
//        JSONPath.set(jsonObject,"data.student[1].age","20");
//        JSONPath.set(jsonObject,"data.student[1].name[1].nn","张三");
//        JSONPath.set(jsonObject,"data.student[1].name[0].nn","张");
//        //获取路径
//        String json = jsonObject.toJSONString();

//        String json = "{\n" +
//                "    \"data\":{\n" +
//                "        \"student\":[\n" +
//                "            {\n" +
//                "                \"name\":\"张三\",\n" +
//                "                \"age\":\"20\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"name\":\"李四\",\n" +
//                "                \"age\":\"21\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"teacher\":[\n" +
//                "            {\n" +
//                "                \"name\":\"peter\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"person\":\"个人\"\n" +
//                "    },\n" +
//                "    \"code\":200,\n" +
//                "    \"message\":\"success\"\n" +
//                "}";

        String json = "{\n" +
                "    \"data\":{\n" +
                "        \"student\":[\n" +
                "            {\n" +
                "                \"name\":\"张三\",\n" +
                "                \"age\":\"20\",\n" +
                "                \"teacher\":[\n" +
                "                    {\n" +
                "                        \"name\":\"peter\",\n" +
                "                        \"project\":\"java\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\":\"jack\",\n" +
                "                        \"project\":\"go\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\":\"李四\",\n" +
                "                \"age\":\"21\",\n" +
                "                \"teacher\":[\n" +
                "                    {\n" +
                "                        \"name\":\"james\",\n" +
                "                        \"project\":\"html\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\":\"mike\",\n" +
                "                        \"project\":\"css\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"person\":\"个人\"\n" +
                "    },\n" +
                "    \"code\":200,\n" +
                "    \"message\":\"success\"\n" +
                "}";
        jsonObject = JSONObject.parseObject(json);


        List<String> listjsonPath= JsonPathUtils.getListJsonPath(jsonObject);
        System.out.println("listJsonPath:"+listjsonPath.toString());
        Map<String,Object> map = new HashMap<>();
        //将路径上的内容替换
//        for(int i=0;i<listjsonPath.size();i++){
//            String  tempPath=  listjsonPath.get(i);
//            String[] fields=tempPath.split("\\.");
//            if (fields.length>0) {
//                String lastFields = fields[fields.length - 1];
//                DocumentContext ext = JsonPath.parse(jsonObject);
//                JsonPath p = JsonPath.compile("$." + tempPath);
//                Object read = ext.read(p);
//                System.out.println( tempPath + ":" + read);
//
//                String readjson = JsonPathUtils.readjson(json, tempPath);
//                System.out.println( readjson + ":" + readjson);
//
//                if (lastFields.equals("nn")) {
//                    //可先取值在对值进行处理
//                    ext.set(p, "吕厚帅");
//                    String NewList = ext.jsonString();
//                    System.out.println("NewsList:" + NewList);
//                }
//                map.put(tempPath,readjson);
//            }
//
//        }


        //将路径的[num] 替换成 [*] 并去重
        List<String> distinctPaths = distinctPath(listjsonPath);

        distinctPaths.forEach(e-> System.out.println(e));

//        distinctPaths.forEach(e->{
//
//            String values = JsonPathUtils.readjson(json, e);
//            System.out.println(values);
//            if (values.startsWith("[") && values.endsWith("]")){
//                //数组
//                JSONArray array = JSONArray.parseArray(values);
////                String substring = values.substring(1, values.length() - 1);
////                String[] arr = new String[]{substring};
//                for (int i = 0; i < array.size(); i++) {
//                    String item = e.replaceAll("\\*", String.valueOf(i));
//                    String itemValue = JsonPathUtils.readjson(json, item);
//                    map.put(item,itemValue);
//                }
//            }else {
//                String itemValue = JsonPathUtils.readjson(json, e);
//                map.put(e,itemValue);
//            }
//        });

        Map<String, List<Integer>> map1 = pathMap(listjsonPath);
        System.out.println(JsonUtils.serialize(map1));

        List<String> realPaths = generateRealPath(map1);
        realPaths.stream()
                .filter(p-> listjsonPath.contains(p))
                .forEach(p-> {
                    System.out.println(p);
                    Object itemValue = JsonPathUtils.readjson(json, p);
                    map.put(p,itemValue);
                });
        String s = JsonPathUtils.warpJson(map);
        System.out.println(s);
    }

    private List<String> generateRealPath(Map<String, List<Integer>> map) {
        List<String> list = new ArrayList<>();

        map.forEach((k,v)->{
            if (CollectionUtils.isEmpty(v)){
                list.add(k);
            }else {
                List<String> indexs = getIndexs(v);
                indexs.forEach(index->{
                    String[] indexArr = index.split(",");
                    String[] pathArr = k.split("[*]");
                    String realPath = pathArr[0];
                    for (int i = 0; i < indexArr.length; i++) {
                        realPath = realPath + indexArr[i]  + pathArr[i+1];
                    }
                    list.add(realPath);
                });
            }
        });

        return list;

    }

    private List<String> getIndexs(List<Integer> params) {
        List<String> indexs = new ArrayList<>();
        List<String[]> list = new ArrayList<>();
        params.forEach(max->{
            String[] arr = new String[max + 1];
            for (int i = 0; i < max + 1; i++) {
                arr[i] = String.valueOf(i);
            }
            list.add(arr);
        });
        JsonPathUtils.enumeration(list, list.get(0),"",indexs);
        return indexs;
    }

    private List<String> distinctPath(List<String> paths) {
        Set<String> set = new HashSet<>();
        paths.forEach(path-> {
            String s = path.replaceAll("[^[0-9]]", "*");
            set.add(s);
        });
        return set.stream().sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

    }

    private Map<String,List<Integer>> pathMap(List<String> paths) {
        Map<String,List<Integer>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        paths.forEach(path-> {
            String s = path.replaceAll("[^[0-9]]", "*");
            set.add(s);
        });
         set.stream().sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());



         set.forEach(s->{
             //找出每个[*]的 * 代表的数量
             if (!s.contains("[*]")){
                 map.put(s, new ArrayList<>());
                 return;
             }
             List<Integer> sizes = new ArrayList<>();
             List<String> spiltPaths = spiltPaths(s, new ArrayList<>());
             spiltPaths.forEach(split->{
                 List<Integer> collect = paths.stream()
                         .filter(p -> {
                             String patten = p.replaceAll("[^[0-9]]", "*");
                             return patten.startsWith(split);
                         })
                         .map(p -> {
                             String size = p.substring(0, split.length()).substring(split.length() - 2, split.length() - 1);
                             return Integer.valueOf(size);
                         })
                         .sorted()
                         .collect(Collectors.toList());
                 sizes.add(collect.get(collect.size()-1));
             });
             map.put(s,sizes);
         });
         return map;
    }


    @Test
    public void split(){
        String path = "a.b[*].c[*].d.e[*]";
        List<String> list = new ArrayList<>();
        List<String> paths = spiltPaths(path, list);
        paths.forEach(s-> System.out.println(s));
    }

    @Test
    public void convert(){
        String json = "{\n" +
                "    \"code\":200,\n" +
                "    \"result\":[\n" +
                "        {\n" +
                "            \"id\":\"001\",\n" +
                "            \"name\":\"一号员工\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"002\",\n" +
                "            \"name\":\"二号员工\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        List<String> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("type",JsonPathUtils.readjson(json,"code"));
        map.put("id",JsonPathUtils.readjson(json,"result[*].id"));
        String json1 = JsonPathUtils.warpJson(map);
        System.out.println(json1);
    }


    @Test
    public void wrap(){

        Map<String,Object> map2 = new HashMap<>();
        String path = "{\n" +
                "  \"condition\":{\n" +
                "      \n" +
                "  },\n" +
                "  \"paging\":{\n" +
                "      \"pageIndex\":1,\n" +
                "      \"pageSize\":3\n" +
                "  }\n" +
                "}";
        List<String> list = JsonPathUtils.getListJsonPathByJsonString(path);

        List<RequestParam> requestParams = new ArrayList<>();


        Map<String,Object> map = new HashMap<>();
        list.forEach(s->{
            RequestParam requestParam = new RequestParam();
            Object value = JsonPathUtils.readjson(path, s);

            String valueContent= "";
            DataType valueType = DataType.String;

            if (value instanceof Integer) {
                valueContent = value.toString();
                valueType = DataType.Integer;
            } else if (value instanceof String) {
                valueContent = value.toString();
            } else if (value instanceof Boolean) {
                valueContent = value.toString();
                valueType = DataType.Boolean;
            } else if (value instanceof net.minidev.json.JSONArray) {
                net.minidev.json.JSONArray  arr = (net.minidev.json.JSONArray) value;
                valueContent = arr.toJSONString();
                valueType = DataType.Array;
            } else if (value instanceof LinkedHashMap) {
                valueContent = JsonUtils.serialize(value);
                valueType = DataType.Map;
            } else if (value instanceof Float) {
                value.toString();
            } else {
                value.toString();
            }
            requestParam.setName(s);
            requestParam.setValueContent(valueContent);
            requestParam.setValueType(valueType);
            requestParams.add(requestParam);
            requestParam.setValueType(valueType);
            map.put(s,value);
        });
        String json = JsonPathUtils.warpJson(map);
        System.out.println(json);

        requestParams.forEach(requestParam -> {
            Object original = getOriginal(requestParam.getValueContent(), requestParam.getValueType());
            map2.put(requestParam.getName(),original);
        });
        String json2 = JsonPathUtils.warpJson(map2);
        System.out.println(json2);

    }

    private Object getOriginal(String value,DataType dataType) {
        Object valueContent = value;
        switch (dataType){
            case Integer:
                valueContent = Integer.valueOf(value);
                break;
            case Long:
                valueContent = Long.valueOf(value);
                break;
            case Float:
                valueContent = Float.valueOf(value);
                break;
            case Timestamp:
                valueContent = new Monment(value,"yyyy-MM-dd HH:mm:ss");
                break;
            case Array:
                valueContent = JsonUtils.deserialize(value, JSONArray.class);
                break;
            case Map:
                valueContent = JsonUtils.deserialize(value, Map.class);
                break;
        }
        return valueContent;
    }


    private List<String> spiltPaths(String path, List<String> list){
        if (list == null){
            list = new ArrayList();
        }
        if (!path.contains("[*]")){
            return list;
        }
        int i = path.indexOf("[*]");
        String s1 = path.substring(0, i + 3);
        list.add(list.size() == 0 ? s1 : list.get(list.size()-1) + s1);
        path = path.substring(i + 3);
        return spiltPaths(path,list);
    }

    @Test
    public void replaceUrl(){
        String url  = "/unity/api/run/{apiId}/web";
        Map<String,Object> pathParamMap =new HashMap<>();
        pathParamMap.put("apiId", "123");
        pathParamMap.put("projectId", "001");
        String finalUrl = url;
        if (!CollectionUtils.isEmpty(pathParamMap)){
            for (Map.Entry<String, Object> entry : pathParamMap.entrySet()) {
                finalUrl = finalUrl.replaceAll("\\{" +  entry.getKey() +  "\\}", entry.getValue() == null  ? "" : entry.getValue().toString());
            }
        }
        System.out.println(finalUrl);
    }



    }
