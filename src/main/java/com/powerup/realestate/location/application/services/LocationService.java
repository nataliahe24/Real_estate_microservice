package com.powerup.realestate.location.application.services;


import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;

import com.powerup.realestate.location.application.dto.response.SaveLocationResponse;



public interface LocationService {
    SaveLocationResponse save(SaveLocationRequest request);

    SaveLocationResponse save(SaveLocationResponse request);
}
