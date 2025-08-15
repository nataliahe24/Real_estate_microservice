package com.powerup.realestate.properties.application.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record SaveVisitScheduleRequest(
        @NotBlank Long sellerId,
        @NotBlank Long propertyId,
        @NotBlank LocalDateTime startDate,
        @NotBlank LocalDateTime endDate
   ) {}