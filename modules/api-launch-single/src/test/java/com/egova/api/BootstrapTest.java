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
                "            \"id\":\"0b35c66b-a7cf-48b7-a9ed-5d2bf4ce4385\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:27:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:27:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:24:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:27:00\",\n" +
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
                "            \"id\":\"237815a8-9c8f-4c89-9b63-22d4aa869bd8\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:54:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:54:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:51:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:54:00\",\n" +
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
                "            \"id\":\"3e110877-680b-45b0-a562-dfb3d8725528\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:42:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:42:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:39:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:42:00\",\n" +
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
                "            \"id\":\"41aa6a00-5881-478f-bff9-a38e32b512c1\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:51:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:51:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:48:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:51:00\",\n" +
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
                "            \"id\":\"4b84bd9f-a3df-4435-acc3-1d502ee2f73f\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:39:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:39:12\",\n" +
                "            \"lastValue\":\"2021-03-18 11:36:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:39:00\",\n" +
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
                "            \"id\":\"580ec3e1-2b2a-4ef6-a430-051cf9bbc416\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 12:18:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 12:18:13\",\n" +
                "            \"lastValue\":\"2021-03-18 12:15:00\",\n" +
                "            \"currentValue\":\"2021-03-18 12:18:00\",\n" +
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
                "            \"id\":\"64deeb27-4b60-4c72-b6e7-cf73be93ac47\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:45:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:45:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:42:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:45:00\",\n" +
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
                "            \"id\":\"680ee999-c39b-49dd-aa72-423a5595bb2f\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:57:01\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:57:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:54:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:57:00\",\n" +
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
                "            \"id\":\"69a088ba-7521-40cc-a15b-12d6eff292c1\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 12:09:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 12:09:12\",\n" +
                "            \"lastValue\":\"2021-03-18 12:06:00\",\n" +
                "            \"currentValue\":\"2021-03-18 12:09:00\",\n" +
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
                "            \"id\":\"76259c0d-2eeb-41a9-8552-71b08bdf7d45\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:48:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:48:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:45:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:48:00\",\n" +
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
                "            \"id\":\"8c4af8fb-5d8d-4390-931b-2a0c312767f7\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:33:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:33:12\",\n" +
                "            \"lastValue\":\"2021-03-18 11:30:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:33:00\",\n" +
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
                "            \"id\":\"8da6d0b6-12d4-4d45-b1cb-5c02701a73e3\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 12:15:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 12:15:13\",\n" +
                "            \"lastValue\":\"2021-03-18 12:12:00\",\n" +
                "            \"currentValue\":\"2021-03-18 12:15:00\",\n" +
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
                "            \"id\":\"960a5333-ecd3-4b72-9d91-93edb2363779\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 12:12:01\",\n" +
                "            \"modifyTime\":\"2021-03-18 12:12:13\",\n" +
                "            \"lastValue\":\"2021-03-18 12:09:00\",\n" +
                "            \"currentValue\":\"2021-03-18 12:12:00\",\n" +
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
                "            \"id\":\"ad050065-fabc-43de-828d-3158fa708a11\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 12:03:00\",\n" +
                "            \"modifyTime\":\"2021-03-18 12:03:12\",\n" +
                "            \"lastValue\":\"2021-03-18 12:00:00\",\n" +
                "            \"currentValue\":\"2021-03-18 12:03:00\",\n" +
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
                "            \"id\":\"bdae7f70-a14c-4837-b589-d69a2ee57a48\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 12:00:01\",\n" +
                "            \"modifyTime\":\"2021-03-18 12:00:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:57:00\",\n" +
                "            \"currentValue\":\"2021-03-18 12:00:00\",\n" +
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
                "            \"id\":\"ca957400-fc0b-4436-8d00-970766b5068f\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:30:01\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:30:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:27:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:30:00\",\n" +
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
                "            \"id\":\"ccae7bd7-be36-4d70-8a39-8e606a6fa3bf\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 11:36:01\",\n" +
                "            \"modifyTime\":\"2021-03-18 11:36:13\",\n" +
                "            \"lastValue\":\"2021-03-18 11:33:00\",\n" +
                "            \"currentValue\":\"2021-03-18 11:36:00\",\n" +
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
                "            \"id\":\"d9596c80-015e-4981-abdb-70aeac7e2e18\",\n" +
                "            \"scheduleId\":\"d7fc99a7-54d9-4983-92e2-a8f73002566a\",\n" +
                "            \"jobId\":\"7a9ac918-dafc-4071-8590-45bb99e75a92\",\n" +
                "            \"logType\":\"SCHEDULE\",\n" +
                "            \"status\":\"SUCCESS\",\n" +
                "            \"executorEndpoint\":\"192.168.101.22:8014\",\n" +
                "            \"creator\":\"admin\",\n" +
                "            \"modifier\":\"admin\",\n" +
                "            \"createTime\":\"2021-03-18 12:06:01\",\n" +
                "            \"modifyTime\":\"2021-03-18 12:06:13\",\n" +
                "            \"lastValue\":\"2021-03-18 12:03:00\",\n" +
                "            \"currentValue\":\"2021-03-18 12:06:00\",\n" +
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
        fieldMappingBase.setConvertRoot("result");
        fieldMappingFacade.parseModel(fieldMappingBase);
        FieldMappingModel model = fieldMappingFacade.parseModel(fieldMappingBase);
        System.out.println(model);
    }
}
