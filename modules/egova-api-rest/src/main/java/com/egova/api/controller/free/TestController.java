package com.egova.api.controller.free;

import com.egova.json.utils.JsonUtils;
import com.egova.web.annotation.Api;
import org.springframework.web.bind.annotation.*;


/**
 * @author Administrator
 * @date 2021/1/15 14:54
 */
@RestController
@RequestMapping(value = "/free/test")
public class TestController {

    @Api
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
