package com.tryc.kafka.service;

import com.tryc.kafka.dto.OperationLogDTO;
import com.tryc.kafka.dto.TopicSearch;
import org.apache.kafka.clients.admin.TopicDescription;

import java.util.Map;

public interface KafkaService {

    Boolean send(OperationLogDTO msg);

    Map<String, TopicDescription> topic(TopicSearch topicSearch);
}
