package com.powerup.realestate.properties.infrastructure.endpoints.rest;

import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.SavePropertyResponse;
import com.powerup.realestate.properties.application.services.PropertyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping("/")
    public ResponseEntity<SavePropertyResponse> save(@RequestBody SavePropertyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(request));
    }
}
