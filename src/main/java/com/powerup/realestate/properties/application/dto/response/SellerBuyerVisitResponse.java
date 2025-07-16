package com.powerup.realestate.properties.application.dto.response;

import java.time.LocalDateTime;

public record SellerBuyerVisitResponse(
    Long id,
    String buyerEmail,
    Long scheduleId,
    Long propertyId,
    String propertyName,
    String propertyAddress,
    String neighborhood,
    String city,
    LocalDateTime startDate,
    LocalDateTime endDate,
    LocalDateTime timestamp
) {} 