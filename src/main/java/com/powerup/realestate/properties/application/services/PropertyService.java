package com.powerup.realestate.properties.application.services;



import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.SavePropertyResponse;


public interface PropertyService {
    SavePropertyResponse save(SavePropertyRequest request);
}

