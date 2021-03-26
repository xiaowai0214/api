package com.egova.api;


import com.egova.SingleApplication;
import com.egova.api.authorization.AuthenticationSupplier;
import com.egova.api.enums.AuthenticationType;
import com.egova.json.utils.JsonUtils;
import com.flagwind.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"grant_type\": \"password\",\n" +
                "  \"client_id\": \"unity-client\",\n" +
                "  \"client_secret\": \"unity\"\n" +
                "}";
        Map map = JsonUtils.deserialize(s, HashMap.class);
        String url = "http://localhost:8780/oauth/extras/token";
        List<AuthenticationSupplier> authenticationSuppliers = Application.resolveAll(AuthenticationSupplier.class);
        for (AuthenticationSupplier authenticationSupplier : authenticationSuppliers) {
            if (authenticationSupplier.match(AuthenticationType.OAuth2)){
                String supply = authenticationSupplier.supply(url, map);
                System.out.println(supply);
            }
        }
    }
}
