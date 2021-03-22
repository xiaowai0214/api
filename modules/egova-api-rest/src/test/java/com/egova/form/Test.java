package com.egova.form;

import com.egova.json.utils.JsonUtils;
import com.flagwind.commons.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args)
    {
        String[] arr1 = { "0", "1"};
        String[] arr2 = { "0", "1","2" };
        String[] arr3 = { "0","1" };
        List<String[]> list = new ArrayList<String[]>();
        list.add(arr1);
        list.add(arr2);
        list.add(arr3);
        List<String> result = new ArrayList<>();
        enumeration(list, arr1, "",result);
        System.out.println(JsonUtils.serialize(result));
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
}
