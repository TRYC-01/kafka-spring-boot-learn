package com.tryc.kafka.controller;

import com.tryc.kafka.dto.OperationLogDTO;
import com.tryc.kafka.dto.TopicSearch;
import com.tryc.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaSendController {
    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/sendAndRecordLog")
    @ResponseBody
    public Boolean send(@RequestBody OperationLogDTO msg) {
        return kafkaService.send(null);
    }

    @PostMapping("/topic")
    @ResponseBody
    public String send(@RequestBody TopicSearch topicSearch) {
        return kafkaService.topic(topicSearch).toString();
    }
}
