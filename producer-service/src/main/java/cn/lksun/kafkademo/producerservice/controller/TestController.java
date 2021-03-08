package cn.lksun.kafkademo.producerservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
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
            for (int i = 0; i < 5; i++) {

//                String key = "key"+(new Random()).nextInt(100);

                ListenableFuture<SendResult<String, Object>> result = kafkaTemplate.send(
                        "test01",
                        i + "",
                        "[" + i + "]" + message
                );


//                test01.addCallback(new ListenableFutureCallback(){
//
//                    @Override
//                    public void onSuccess(Object o) {
//                        System.out.println(o.toString());
//                        System.out.println("ok");
//                    }
//
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        System.out.println("error");
//                    }
//                });
//                ProducerRecord<String, String> record = new ProducerRecord<String, String>("test01",key,message);
//                kafkaTemplate.send(record);

            }
            log.info("send successfully");
            return "ok";
        } catch (Exception e) {
            log.error("send failed ", e);
            return "error";
        }
    }


}
