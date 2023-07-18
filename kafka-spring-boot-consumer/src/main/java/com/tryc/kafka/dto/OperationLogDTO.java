package com.tryc.kafka.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class OperationLogDTO {
    private String operation;
    private LocalDateTime dateTime;
    private String operator;
}
