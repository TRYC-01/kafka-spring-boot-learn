package com.tryc.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyListener extends AbstractConsumerSeekAware {
//    @KafkaListener(topics = "${business.topic}", groupId = "log-all")
//    public void listAllLog(String msg) {
//        log.info("listAllLog ===>{}",msg);
//    }

}
