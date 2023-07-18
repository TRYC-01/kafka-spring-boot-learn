package com.tryc.kafka.service;

import com.tryc.kafka.dto.OperationLogDTO;
import com.tryc.kafka.listener.MyListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private MyListener myListener;



    @Value("${business.topic}")
    private String topic;

    @KafkaListener(topics = {"${business.topic}"}, groupId = "group1")
    public void listenTopic(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        log.info("received msg ===> {},ack:{}", record, ack);
        try {
//            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<OperationLogDTO> queryAllLog() {
        myListener.seekToBeginning();
        return new ArrayList<>();

    }
}
