package com.powerup.realestate.properties.application.dto.request;
import java.math.BigDecimal;
import java.time.LocalDate;

public record SavePropertyRequest(String name,String description,Long categoryId,Integer rooms, Integer bathrooms,
                                 BigDecimal price, Long locationId, LocalDate activePublicationDate,
                                 String publicationStatus, LocalDate publicationDate) {

}

