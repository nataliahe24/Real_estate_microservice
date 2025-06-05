package com.powerup.realestate.properties.application.dto.request;


public record ScheduleBuyerVisitRequest(
        Long scheduleId,
        String buyerEmail
) {} 