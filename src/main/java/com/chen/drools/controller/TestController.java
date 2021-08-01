package com.chen.drools.controller;

import com.chen.drools.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-08-01
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public void test(){
        testService.test();
    }
}
