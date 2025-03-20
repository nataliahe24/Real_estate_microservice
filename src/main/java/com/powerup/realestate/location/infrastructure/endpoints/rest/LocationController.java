package com.powerup.realestate.location.infrastructure.endpoints.rest;

import com.powerup.realestate.location.application.dto.response.LocationResponse;
import com.powerup.realestate.location.application.dto.request.SaveLocationRequest;
import com.powerup.realestate.location.application.dto.response.SaveLocationResponse;
import com.powerup.realestate.location.application.services.LocationService;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;


    @PostMapping("/")
    public ResponseEntity<SaveLocationResponse> save(@RequestBody SaveLocationRequest saveLocationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.save(saveLocationRequest));
    }

    @GetMapping("/")
    public ResponseEntity<PageResult<LocationResponse>> getLocationsPage(@RequestParam (required = false) String searchText,
                                                                         @RequestParam Integer page,
                                                                         @RequestParam Integer size,
                                                                         @RequestParam boolean orderAsc){
        return ResponseEntity.ok(locationService.getLocations(searchText, page, size, orderAsc));
    }

}


