package com.powerup.realestate.properties.infrastructure.endpoints.rest;

import com.powerup.realestate.properties.application.dto.request.FilterVisitScheduleRequest;
import com.powerup.realestate.properties.application.dto.request.SaveVisitScheduleRequest;
import com.powerup.realestate.properties.application.dto.response.SaveVisitScheduleResponse;
import com.powerup.realestate.properties.application.dto.response.VisitScheduleResponse;
import com.powerup.realestate.properties.application.services.VisitScheduleService;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visit-schedules")
@RequiredArgsConstructor
public class VisitScheduleController {
    private final VisitScheduleService visitScheduleService;

    @PostMapping("/")
    @Operation(
            summary = "Endpoint protegido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<SaveVisitScheduleResponse> save(@RequestBody SaveVisitScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitScheduleService.save(request));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<VisitScheduleResponse>> getSchedulesByPropertyId(@PathVariable Long propertyId) {
        return ResponseEntity.ok(visitScheduleService.getSchedulesByPropertyId(propertyId));
    }

    @GetMapping("/seller/{sellerId}")
    @Operation(
            summary = "Endpoint protegido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<List<VisitScheduleResponse>> getSchedulesBySellerId(@PathVariable Long sellerId) {
        return ResponseEntity.ok(visitScheduleService.getSchedulesBySellerId(sellerId));
    }

    @GetMapping("/")
    public ResponseEntity<PageResult<VisitScheduleResponse>> getSchedules(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam Integer size) {
        return ResponseEntity.ok(visitScheduleService.getSchedules(page, size));
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Filtrar horarios de visita disponibles",
    security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<PageResult<VisitScheduleResponse>> filterSchedules(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam Integer size) {
        
        FilterVisitScheduleRequest request = new FilterVisitScheduleRequest(
                startDate,
                endDate,
                location,
                page,
                size
        );
        
        return ResponseEntity.ok(visitScheduleService.getFilteredSchedules(request));
    }
} 