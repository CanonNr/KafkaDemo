package cn.lksun.kafkademo.consumerservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @Value("${server.port}")
    String port;
    @GetMapping("")
    public String test1(){
        return "test1："+port;
    }
}
