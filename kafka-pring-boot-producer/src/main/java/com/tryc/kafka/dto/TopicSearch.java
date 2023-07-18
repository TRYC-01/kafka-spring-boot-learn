package com.tryc.kafka.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TopicSearch {
    private List<String> topics;
}
