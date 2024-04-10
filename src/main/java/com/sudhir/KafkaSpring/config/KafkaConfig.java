package com.sudhir.KafkaSpring.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static String topicName = "kafka4";
    @Bean
    public NewTopic createNewTopic(){
        return new NewTopic(topicName, 5, (short)1);
    }
}
