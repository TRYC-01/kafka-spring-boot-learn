package com.tryc.kafka.service;

import com.tryc.kafka.dto.OperationLogDTO;

import java.util.List;

public interface KafkaService {


    List<OperationLogDTO> queryAllLog();
}
