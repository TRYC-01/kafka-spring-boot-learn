package com.tryc.kafka.controller;

import com.tryc.kafka.dto.OperationLogDTO;
import com.tryc.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaReceiveController {
    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/receive")
    @ResponseBody
    public List<OperationLogDTO> send(@RequestBody OperationLogDTO msg) {
        return kafkaService.queryAllLog();
    }
}
