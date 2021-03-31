package com.egova.api;


import com.egova.SingleApplication;
import com.egova.api.facade.FieldMappingFacade;
import com.egova.api.model.FieldMappingBase;
import com.egova.api.model.FieldMappingModel;
import com.flagwind.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SingleApplication.class)
@Profile("dev")
public class BootstrapTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 单元测试
     */
    @Test
    public void run() {
        logger.info("run test cases...");
    }

    /**
     * 测试api认证
     */
    @Test
    public void apiAuthorization() {
        String s = "{\n" +
                "    \"hasError\":false,\n" +
                "    \"result\":[\n" +
                "        {\n" +
                "            \"id\":\"10004\",\n" +
                "            \"username\":\"sjh\",\n" +
                "            \"name\":\"sjh\",\n" +
                "            \"sex\":\"Man\",\n" +
                "            \"email\":null,\n" +
                "            \"phone\":null,\n" +
                "            \"disabled\":false,\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":null,\n" +
                "            \"createTime\":\"2020-12-23 11:03:46\",\n" +
                "            \"remark\":null,\n" +
                "            \"phonetic\":\"xitongguanliyuan xtgly\",\n" +
                "            \"regionId\":\"420000\",\n" +
                "            \"photo\":null,\n" +
                "            \"modifyTime\":\"2021-03-09 10:40:44\",\n" +
                "            \"userType\":\"Server\",\n" +
                "            \"terminalType\":\"Web\",\n" +
                "            \"departmentId\":null,\n" +
                "            \"personId\":null,\n" +
                "            \"accountExpireTime\":null,\n" +
                "            \"tenantId\":null,\n" +
                "            \"sign\":null,\n" +
                "            \"position\":\"\",\n" +
                "            \"sexName\":\"男士\",\n" +
                "            \"regionName\":null,\n" +
                "            \"userTypeName\":\"后台用户\",\n" +
                "            \"_terminalType\":{\n" +
                "                \"name\":\"Web\",\n" +
                "                \"text\":\"Web\",\n" +
                "                \"value\":\"3\"\n" +
                "            },\n" +
                "            \"departmentName\":null,\n" +
                "            \"personName\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"10005\",\n" +
                "            \"username\":\"admin\",\n" +
                "            \"name\":\"系统管理员\",\n" +
                "            \"sex\":\"Man\",\n" +
                "            \"email\":null,\n" +
                "            \"phone\":null,\n" +
                "            \"disabled\":false,\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":null,\n" +
                "            \"createTime\":\"2020-12-23 11:03:46\",\n" +
                "            \"remark\":null,\n" +
                "            \"phonetic\":\"xitongguanliyuan xtgly\",\n" +
                "            \"regionId\":\"420000\",\n" +
                "            \"photo\":null,\n" +
                "            \"modifyTime\":\"2021-03-30 20:29:46\",\n" +
                "            \"userType\":\"Server\",\n" +
                "            \"terminalType\":\"IOS\",\n" +
                "            \"departmentId\":null,\n" +
                "            \"personId\":null,\n" +
                "            \"accountExpireTime\":null,\n" +
                "            \"tenantId\":null,\n" +
                "            \"sign\":null,\n" +
                "            \"position\":\"\",\n" +
                "            \"sexName\":\"男士\",\n" +
                "            \"regionName\":null,\n" +
                "            \"userTypeName\":\"后台用户\",\n" +
                "            \"_terminalType\":{\n" +
                "                \"name\":\"IOS\",\n" +
                "                \"text\":\"苹果\",\n" +
                "                \"value\":\"1\"\n" +
                "            },\n" +
                "            \"departmentName\":null,\n" +
                "            \"personName\":null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"10070\",\n" +
                "            \"username\":\"oQM1O5M9E0eaAtuREdoIxXTO2jh4\",\n" +
                "            \"name\":\"浩之然\",\n" +
                "            \"sex\":\"Man\",\n" +
                "            \"email\":null,\n" +
                "            \"phone\":\"Hubei\",\n" +
                "            \"disabled\":false,\n" +
                "            \"creator\":\"wechat\",\n" +
                "            \"modifier\":\"wechat\",\n" +
                "            \"createTime\":\"2021-03-13 15:14:12\",\n" +
                "            \"remark\":null,\n" +
                "            \"phonetic\":\"haozhiran hzr\",\n" +
                "            \"regionId\":\"420000\",\n" +
                "            \"photo\":\"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKgNQbtic3oYSbfqJwMv74QTPNwdYD8CPIXJMb7mLUa1bD3KpxeibaflJSOwgWj9lkPpkBicVO13Mpxw/132\",\n" +
                "            \"modifyTime\":\"2021-03-26 14:30:03\",\n" +
                "            \"userType\":\"Server\",\n" +
                "            \"terminalType\":\"IOS\",\n" +
                "            \"departmentId\":null,\n" +
                "            \"personId\":null,\n" +
                "            \"accountExpireTime\":null,\n" +
                "            \"tenantId\":null,\n" +
                "            \"sign\":null,\n" +
                "            \"position\":\"\",\n" +
                "            \"sexName\":\"男士\",\n" +
                "            \"regionName\":null,\n" +
                "            \"userTypeName\":\"后台用户\",\n" +
                "            \"_terminalType\":{\n" +
                "                \"name\":\"IOS\",\n" +
                "                \"text\":\"苹果\",\n" +
                "                \"value\":\"1\"\n" +
                "            },\n" +
                "            \"departmentName\":null,\n" +
                "            \"personName\":null\n" +
                "        }\n" +
                "    ],\n" +
                "    \"message\":null,\n" +
                "    \"tag\":null,\n" +
                "    \"totalCount\":227\n" +
                "}";
        FieldMappingFacade fieldMappingFacade = Application.resolve(FieldMappingFacade.class);
        FieldMappingBase fieldMappingBase = new FieldMappingBase();
        fieldMappingBase.setOriginalJson(s);
        fieldMappingBase.setCollapse(true);
        fieldMappingFacade.parseModel(fieldMappingBase);
        FieldMappingModel model = fieldMappingFacade.parseModel(fieldMappingBase);
        System.out.println(model);
    }
}
