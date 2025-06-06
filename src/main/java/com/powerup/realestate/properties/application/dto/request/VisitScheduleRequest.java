package com.powerup.realestate.properties.application.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record VisitScheduleRequest(
        @NotBlank LocalDateTime startDate,
        @NotBlank LocalDateTime endDate,
        @NotBlank String location,
        @NotBlank Integer page,
        @NotBlank Integer size
) {} 