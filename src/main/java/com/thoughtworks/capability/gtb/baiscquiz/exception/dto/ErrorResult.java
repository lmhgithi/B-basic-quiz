package com.thoughtworks.capability.gtb.baiscquiz.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    String timestamp;
    Integer status;
    String error;
    String message;
}
