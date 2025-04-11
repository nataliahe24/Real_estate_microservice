package com.powerup.realestate.properties.application.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SavePropertyRequest(@NotBlank String name,@NotBlank String address, @NotBlank String description,@NotBlank Long category,@NotBlank Integer rooms,@NotBlank Integer bathrooms,
                                  @NotBlank BigDecimal price, @NotBlank Long location,@NotBlank LocalDate activePublicationDate) {

}

