package com.powerup.realestate.properties.application.dto.response;

import java.time.LocalDateTime;

public record SaveVisitScheduleResponse(
    String message,
    LocalDateTime timestamp
) {} 