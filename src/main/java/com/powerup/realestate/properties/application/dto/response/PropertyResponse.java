package com.powerup.realestate.properties.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PropertyResponse(Long id, String name, String address, String description, String category, Integer rooms,
                               Integer bathrooms, BigDecimal price, String neighborhood, String city, String department,
                               LocalDate activePublicationDate, String publicationStatus, Long sellerId) {

}
