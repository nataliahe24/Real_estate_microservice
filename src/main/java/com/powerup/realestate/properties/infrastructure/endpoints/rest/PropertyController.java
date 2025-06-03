package com.powerup.realestate.properties.infrastructure.endpoints.rest;

import com.powerup.realestate.properties.application.dto.request.SavePropertyRequest;
import com.powerup.realestate.properties.application.dto.response.PropertyResponse;
import com.powerup.realestate.properties.application.dto.response.SavePropertyResponse;
import com.powerup.realestate.properties.application.services.PropertyService;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping("/")
    @Operation(
            summary = "Endpoint protegido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<SavePropertyResponse> save(@RequestBody SavePropertyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.save(request));
    }

    @GetMapping("/")
    @Operation(
            summary = "Endpoint protegido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<PageResult<PropertyResponse>> getProperties(@RequestParam(defaultValue = "0") Integer page,
                                                                      @RequestParam Integer size,
                                                                      @RequestParam (required = false) Long sellerId,
                                                                      @RequestParam(required = false) String location,
                                                                      @RequestParam(required = false) String category,
                                                                      @RequestParam(required = false) Integer rooms,
                                                                      @RequestParam(required = false) Integer bathrooms,
                                                                      @RequestParam(required = false) Double minPrice,
                                                                      @RequestParam(required = false) Double maxPrice,
                                                                      @RequestParam(required = false) String sortBy,
                                                                      @RequestParam boolean orderAsc) {

        return ResponseEntity.ok(
                propertyService.getPropertiesByFiltersAndOrder(
                        sellerId,
                        page,
                        size,
                        location,
                        category,
                        rooms,
                        bathrooms,
                        minPrice,
                        maxPrice,
                        sortBy,
                        orderAsc
                )
        );
    }

    @GetMapping("/list")
    @Operation(
            summary = "Endpoint protegido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<PageResult<PropertyResponse>> getAllProperties(@RequestParam(defaultValue = "0") Integer page,
                                                                      @RequestParam Integer size,
                                                                      @RequestParam(required = false) String location,
                                                                      @RequestParam(required = false) String category,
                                                                      @RequestParam boolean orderAsc) {

        return ResponseEntity.ok(
                propertyService.getProperties(
                        page,
                        size,
                        location,
                        category,
                        orderAsc
                )
        );
    }
}