package com.sudhir.KafkaSpring.api;

import com.sudhir.KafkaSpring.pojo.Customer;
import com.sudhir.KafkaSpring.service.KafkaPMsgPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class KafkaController {
    @Autowired
    KafkaPMsgPublisher  kafkaPMsgPublisherService;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try{
            for (int i = 0; i < 10000; i++) {
                kafkaPMsgPublisherService.sendMesgToTopic(message+" : "+i);
                kafkaPMsgPublisherService.sendMesgToTopicByCreatingTopicName(message+" : "+i);
            }
            return ResponseEntity.ok("Message Published!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/cust")
    public ResponseEntity<?> publishCustomer(@RequestBody Customer customer){
        try{
            kafkaPMsgPublisherService.sendEventsToTopicByCreatingTopicNameCustomer(customer);
            return ResponseEntity.ok("Published");
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }

}
