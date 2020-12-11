package cn.lksun.kafkademo.producerservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("test")
public class TestController {
    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${server.port}")
    String port;

    @GetMapping("")
    public String test1(){
        return "test1："+port;
    }

    @GetMapping("push")
    public String push(@RequestParam("message") String message){
        try {
            String key = "key"+(new Random()).nextInt(100);
            log.info("kafka message : {}",message);
            kafkaTemplate.send("test", message);
            log.info("send successfully");
            return "ok";
        } catch (Exception e) {
            log.error("send failed ", e);
            return "error";
        }
    }


}
