package com.powerup.realestate.properties.infrastructure.exceptionshandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BuyerVisitExceptionResponse {
    private final String message;
    private final LocalDateTime timestamp;
} 