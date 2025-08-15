package com.powerup.realestate.properties.application.dto.request;



import java.math.BigDecimal;
import java.time.LocalDate;

public record SavePropertyRequest(
        String name,
        String address,
        String description,
        Long category,
        Integer rooms,
        Integer bathrooms,
        BigDecimal price,
        Long location,
        LocalDate activePublicationDate,
        Long sellerId
) {
}

