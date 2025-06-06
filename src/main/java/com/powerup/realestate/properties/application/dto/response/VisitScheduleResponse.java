package com.powerup.realestate.properties.application.dto.response;

import java.time.LocalDateTime;


public record VisitScheduleResponse (
     Long id,
     Long sellerId,
     Long propertyId,
     String propertyName,
     String neighborhood,
     String city,
     String address,
     LocalDateTime startDate,
     LocalDateTime endDate){
}