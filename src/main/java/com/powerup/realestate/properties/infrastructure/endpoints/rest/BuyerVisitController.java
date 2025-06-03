package com.powerup.realestate.properties.infrastructure.endpoints.rest;

import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;
import com.powerup.realestate.properties.application.services.BuyerVisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buyer-visits")
@RequiredArgsConstructor
public class BuyerVisitController {

    private final BuyerVisitService buyerVisitService;
    
    @PostMapping("/")
    @Operation(
            summary = "Agendar una visita de comprador",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ScheduleBuyerVisitResponse> scheduleBuyerVisit(
            @Valid @RequestBody ScheduleBuyerVisitRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(buyerVisitService.scheduleBuyerVisit(request));
    }
    
    @GetMapping("/")
    @Operation(
            summary = "Obtener visitas",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<List<ScheduleBuyerVisitResponse>> getBuyerVisitsByScheduleId(
            @RequestParam String buyerEmail) {
        return ResponseEntity.ok(buyerVisitService.getBuyerVisitsByEmail(buyerEmail));
    }
    
    @DeleteMapping("/cancel")
    @Operation(
            summary = "Cancelar una visita agendada",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<String> cancelBuyerVisit(@RequestParam Long visitId) {
        buyerVisitService.cancelBuyerVisit(visitId);
        return ResponseEntity.ok("Visita cancelada correctamente");
    }
} 