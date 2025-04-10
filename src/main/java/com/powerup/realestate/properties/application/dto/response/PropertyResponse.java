package com.powerup.realestate.properties.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PropertyResponse(Long id, String name,String description,Long category,Integer rooms, Integer bathrooms,
    BigDecimal price, Long location, LocalDate activePublicationDate,
    String publicationStatus) {

    }
