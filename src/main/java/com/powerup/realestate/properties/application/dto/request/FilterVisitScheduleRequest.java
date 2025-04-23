package com.powerup.realestate.properties.application.dto.request;

import java.time.LocalDateTime;

public record FilterVisitScheduleRequest(
    LocalDateTime startDate,
    LocalDateTime endDate,
    String location,
    Integer page,
    Integer size
) {} 