package com.egova.api.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.egova.json.utils.JsonUtils;
import com.flagwind.commons.StringUtils;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class JsonPathUtils {
    //获取jsonPath
    public static List<String> getListJsonPathByJsonString(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        return getListJsonPath(jsonObject);
    }

    public static List<String> getListJsonPath(JSONObject jsonObject) {

        List<String> jsonPaths= JSONPathEnhanced.paths(jsonObject).keySet().stream().collect(Collectors.toList());
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
    public static Object readjson(String json, String jsonPath) {
        try {
            Object value = JsonPath.read(json, jsonPath, new Predicate[0]);
            return value;
//            if (value instanceof Integer) {
//                return value.toString();
//            } else if (value instanceof String) {
//                return value.toString();
//            } else if (value instanceof Boolean) {
//                return value.toString();
//            } else if (value instanceof net.minidev.json.JSONArray) {
//                net.minidev.json.JSONArray  arr = (net.minidev.json.JSONArray) value;
//                if (!arr.isEmpty()) {
//                    return arr.toJSONString();
//                } else
//                    return "";
//            } else if (value instanceof LinkedHashMap) {
//                return value.toString();
//            } else if (value instanceof Float) {
//                return value.toString();
//            } else {
//                return value.toString();
//            }
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
        return JsonUtils.serialize(jsonObject);
    }

    public static String warpJson(List<String> paths , String json){
        Map<String,Object> map = new HashMap<>();
        paths.forEach(path->
            map.put(path, readjson(json,path))
        );
        return warpJson(map);
    }


    public static void enumeration(List<String[]> list, String[] first, String origin, List<String> result)
    {
        for (int i = 0; i < list.size(); i++)
        {
            //取得当前的数组
            if (i == list.indexOf(first))
            {
                //迭代数组
                for (String st : first)
                {
                    st = StringUtils.isBlank(origin) ? st : origin + "," + st;
                    if (i < list.size() - 1)
                    {
                        enumeration(list, list.get(i + 1), st, result);
                    }
                    else if (i == list.size() - 1)
                    {
                        System.out.println(st);
                        result.add(st);
                    }
                }
            }
        }
    }

    public static List<String> generateRealPath(String originJson){
        List<String> paths = getListJsonPathByJsonString(originJson);
        //将路径的[num] 替换成 [*] 并去重
        List<String> distinctPaths = distinctPath(paths);
        Map<String, List<Integer>> map = pathMap(distinctPaths);
        return generateRealPath(map);
    }

    public static List<String> generateRealPath(Map<String, List<Integer>> map) {
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

    public static List<String> getIndexs(List<Integer> params) {
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

    public static List<String> distinctPath(List<String> paths) {
        Set<String> set = new HashSet<>();
        paths.forEach(path-> {
            String s = path.replaceAll("[^[0-9]]", "*");
            set.add(s);
        });
        return set.stream().sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
    }

    public static Map<String,List<Integer>> pathMap(List<String> paths) {
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

    public static List<String> spiltPaths(String path, List<String> list){
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
