package com.tryc.kafka.service;

import com.alibaba.fastjson.JSONObject;
import com.tryc.kafka.dto.OperationLogDTO;
import com.tryc.kafka.dto.TopicSearch;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Value("${business.topic}")
    private String topic;

    @Autowired
    private KafkaAdmin kafkaAdmin;

    public static final String[] OPERATION = new String[]{"新增", "修改", "删除", "审核", "回退"};
    public static final String[] OPERATOR = new String[]{"Jerry", "Jock", "Jack", "Wiliam", "Wilkaes"};

    @Override
    public Boolean send(OperationLogDTO msg) {
        if (msg == null) {
            Random random = new Random();
            msg = new OperationLogDTO();
            msg.setOperator(OPERATOR[random.nextInt(5)]);
            msg.setOperation(OPERATION[random.nextInt(5)]);
            msg.setDateTime(LocalDateTime.now());
        }
        String s = JSONObject.toJSONString(msg);
        log.info("msg ===> {}", s);
        ListenableFuture send = kafkaTemplate.send(topic, s);
        send.addCallback(new KafkaSendCallback() {
            @Override
            public void onFailure(KafkaProducerException e) {
                log.error("send msg error ===> {}", e);
            }

            @Override
            public void onSuccess(Object result) {
                log.info("send msg success ===> {}", result);
            }
        });

        return true;
    }

    @Override
    public Map<String, TopicDescription> topic(TopicSearch topicSearch) {
        List<String> topics = topicSearch.getTopics();
        String[] strings = topics.toArray(new String[topics.size()]);
        Map<String, TopicDescription> topicsInfo = kafkaAdmin.describeTopics(strings);
        log.info("topics info ===> {}", topicsInfo);
        return topicsInfo;
    }
}
