package cn.lksun.kafkademo.consumerservice.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerListener {

    @Value("${server.port}")
    String port;

    @Value("${spring.kafka.consumer.group-id}")
    String groupId;

    @Value("${test.partition-id}")
    String partitionId;

//    @KafkaListener(topics = "test01")
//    @KafkaListener(topics = "#{'${spring.kafka.topics}'.split('\\\\ ')}")
    @KafkaListener(
        topicPartitions ={
            @TopicPartition(topic = "test01", partitions = {"#{${test.partition-id}}"})
        }
    )
    public void onMessage(String message) throws InterruptedException {
        log.info("message[{}]  :  {} ,{}",port,message,partitionId);
        Thread.sleep(100);
    }

}
