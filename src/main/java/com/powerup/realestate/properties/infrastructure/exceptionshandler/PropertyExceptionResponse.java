package com.powerup.realestate.properties.infrastructure.exceptionshandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PropertyExceptionResponse {
    private String message;
    private LocalDateTime timestamp;
} 