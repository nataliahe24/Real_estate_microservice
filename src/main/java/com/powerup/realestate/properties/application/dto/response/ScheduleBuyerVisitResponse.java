package com.powerup.realestate.properties.application.dto.response;

import java.time.LocalDateTime;

public record ScheduleBuyerVisitResponse(
    Long id,
    String buyerEmail,
    Long scheduleId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    LocalDateTime timestamp
) {} 