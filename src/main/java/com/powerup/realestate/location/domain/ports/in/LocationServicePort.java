package com.powerup.realestate.location.domain.ports.in;

import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.location.domain.model.LocationModel;


public interface LocationServicePort {
    void save(LocationModel locationModel);
}
