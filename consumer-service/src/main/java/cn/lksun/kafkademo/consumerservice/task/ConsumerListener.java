package cn.lksun.kafkademo.consumerservice.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerListener {

    @KafkaListener(topics = "test")
    public void onMessage(String message){
        log.info("message:{} ",message);
    }

}
