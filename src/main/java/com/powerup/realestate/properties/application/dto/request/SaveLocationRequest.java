package com.powerup.realestate.properties.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SaveLocationRequest(
        @NotBlank String cityName,
        @NotBlank String neighborhood
) {
}
