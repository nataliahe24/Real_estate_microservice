package com.powerup.realestate.properties.application.dto.request;

import java.time.LocalDateTime;

public record SaveVisitScheduleRequest(
    Long sellerId,
    Long propertyId,
    LocalDateTime startDate,
    LocalDateTime endDate
) {} 