package com.egova.form;

import com.google.common.base.CaseFormat;

public class Test2 {
    public static void main(String[] args) {
        String name = "fileSizeUnit";
        String lower_case = toLowerCaseFirstOne(name);
        String lower_hyphen = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, name);
        System.out.println("lower_case:" + lower_case );
        System.out.println("lower_hyphen:" + lower_hyphen );
    }

    private static String toLowerCaseFirstOne(String value) {
        return Character.isLowerCase(value.charAt(0)) ? value : Character.toLowerCase(value.charAt(0)) + value.substring(1);
    }
}
