package com.powerup.realestate.location.domain.ports.in;

import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;


public interface LocationServicePort {
    void save(SaveLocationRequest saveLocationRequest);
}
