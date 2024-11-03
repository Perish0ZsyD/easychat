package com.easychat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Siyuan
 * @date 2024/11/03/21:52
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "Hello World!";
    }
}
