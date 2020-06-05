package com.study.starter.demo.controller;

import com.study.starter.hello.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ChengJianSheng
 * @date 2019-05-26
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private HelloService helloService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloService.sayHello(name);
    }

    @GetMapping("/info")
    public String info() {
        return helloService.helloWorld();
    }

}
