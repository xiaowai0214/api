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
                "            \"id\":\"0adcfa18-22c9-4f9e-8910-a58184083e3a\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:21:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:21:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:18:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:21:00\",\n" +
                "            \"totalReadRecords\":0,\n" +
                "            \"totalDirtyRecords\":0,\n" +
                "            \"totalFilterRecords\":0,\n" +
                "            \"totalWriteRecords\":0,\n" +
                "            \"scheduleName\":\"人员信息接入调度\",\n" +
                "            \"jobName\":\"人员信息接入[kafka]\",\n" +
                "            \"_logType\":{\n" +
                "                \"name\":\"SCHEDULE\",\n" +
                "                \"text\":\"调度\",\n" +
                "                \"value\":\"0\"\n" +
                "            },\n" +
                "            \"_status\":{\n" +
                "                \"name\":\"SUCCESS\",\n" +
                "                \"text\":\"成功\",\n" +
                "                \"value\":\"1\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"fb6a6be8-2db7-459f-aae2-02f012e86951\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:24:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:24:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:21:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:24:00\",\n" +
                "            \"totalReadRecords\":0,\n" +
                "            \"totalDirtyRecords\":0,\n" +
                "            \"totalFilterRecords\":0,\n" +
                "            \"totalWriteRecords\":0,\n" +
                "            \"scheduleName\":\"人员信息接入调度\",\n" +
                "            \"jobName\":\"人员信息接入[kafka]\",\n" +
                "            \"_logType\":{\n" +
                "                \"name\":\"SCHEDULE\",\n" +
                "                \"text\":\"调度\",\n" +
                "                \"value\":\"0\"\n" +
                "            },\n" +
                "            \"_status\":{\n" +
                "                \"name\":\"SUCCESS\",\n" +
                "                \"text\":\"成功\",\n" +
                "                \"value\":\"1\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"message\":null,\n" +
                "    \"tag\":null,\n" +
                "    \"totalCount\":20\n" +
                "}";
        FieldMappingFacade fieldMappingFacade = Application.resolve(FieldMappingFacade.class);
        FieldMappingBase fieldMappingBase = new FieldMappingBase();
        fieldMappingBase.setOriginalJson(s);
        fieldMappingBase.setCollapse(true);
//        fieldMappingBase.setConvertRoot("result");
        fieldMappingFacade.parseModel(fieldMappingBase);
        FieldMappingModel model = fieldMappingFacade.parseModel(fieldMappingBase);
        System.out.println(model);
    }
}
