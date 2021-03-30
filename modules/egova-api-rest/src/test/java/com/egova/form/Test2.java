package com.egova.form;

import com.google.common.base.CaseFormat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test2 {
    public static void test(String[] args) {
        String name = "fileSizeUnit";
        String lower_case = toLowerCaseFirstOne(name);
        String lower_hyphen = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, name);
        System.out.println("lower_case:" + lower_case );
        System.out.println("lower_hyphen:" + lower_hyphen );
    }

    private static String toLowerCaseFirstOne(String value) {
        return Character.isLowerCase(value.charAt(0)) ? value : Character.toLowerCase(value.charAt(0)) + value.substring(1);
    }


    public static void main(String[] args) throws ScriptException {
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        // evaluate JavaScript code from String
        engine.eval("print('Hello, World')");
    }


}
