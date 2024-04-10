package com.sudhir.KafkaSpring.service;

import com.sudhir.KafkaSpring.config.KafkaConfig;
import com.sudhir.KafkaSpring.pojo.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
@PropertySource("application.yml")
public class KafkaPMsgPublisher {

    private final KafkaConfig kafkaConfig;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    String topicName;

    public KafkaPMsgPublisher(KafkaConfig kafkaConfig, KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaConfig = kafkaConfig;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMesgToTopic(String message){
        topicName = kafkaConfig.createNewTopic().name();
        System.out.println("\n topic name:"+topicName);
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topicName, message);
        completableFuture.whenComplete((result, exception) -> {
           if (exception == null){
               System.out.println("Sent message: "+message+" and with offset: "+result.getRecordMetadata().offset());
           } else{
               System.out.println("Exception: "+exception.getMessage());
           }
        });
    }


    public void sendMesgToTopicByCreatingTopicName(String message){
        System.out.println("\n topic name:"+topicName);
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topicName, message);
        completableFuture.whenComplete((result, exception) -> {
            if (exception == null){
                System.out.println("Sent message: "+message+" and with offset: "+result.getRecordMetadata().offset());
            } else{
                System.out.println("Exception: "+exception.getMessage());
            }
        });
    }


    public void sendEventsToTopicByCreatingTopicNameCustomer(Customer message){
        System.out.println("\n topic name:"+topicName);
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topicName, message);
        completableFuture.whenComplete((result, exception) -> {
            if (exception == null){
                System.out.println("Sent message: "+message+" and with offset: "+result.getRecordMetadata().offset());
            } else{
                System.out.println("Exception: "+exception.getMessage());
            }
        });
    }
}
