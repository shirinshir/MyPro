package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private Instant timestamp;
    private String path;
    private String errorType;
    private Map<String, String> validationErrors;
}