package com.tryc.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate kafkaTemplate;



    @KafkaListener(topics = {"${business.topic}"})
    public void listenTopic(ConsumerRecord<?,?> record, Acknowledgment ack){
        log.info("received msg ===> {},ack:{}",record,ack);
        try {
//            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
