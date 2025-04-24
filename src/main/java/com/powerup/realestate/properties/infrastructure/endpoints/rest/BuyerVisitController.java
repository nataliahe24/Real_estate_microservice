package com.powerup.realestate.properties.infrastructure.endpoints.rest;

import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;
import com.powerup.realestate.properties.application.services.BuyerVisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buyer-visits")
@Tag(name = "Buyer Visits", description = "API para gestionar las visitas de compradores")
public class BuyerVisitController {

    private final BuyerVisitService buyerVisitService;
    
    @Autowired
    public BuyerVisitController(BuyerVisitService buyerVisitService) {
        this.buyerVisitService = buyerVisitService;
    }
    
    @PostMapping
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
    
    @GetMapping("/schedule/{scheduleId}")
    @Operation(
            summary = "Obtener visitas para un horario específico",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<List<ScheduleBuyerVisitResponse>> getBuyerVisitsByScheduleId(
            @PathVariable Long scheduleId) {
        return ResponseEntity.ok(buyerVisitService.getBuyerVisitsByScheduleId(scheduleId));
    }
    
    @PostMapping("/sync-counters/{scheduleId}")
    @Operation(
            summary = "Sincronizar contador de agendamientos para un horario específico",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<String> syncScheduledBuyersCounter(@PathVariable Long scheduleId) {
        buyerVisitService.syncScheduledBuyersCounter(scheduleId);
        return ResponseEntity.ok("Contador sincronizado correctamente");
    }
    
    @DeleteMapping("/{visitId}")
    @Operation(
            summary = "Cancelar una visita agendada",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<String> cancelBuyerVisit(@PathVariable Long visitId) {
        buyerVisitService.cancelBuyerVisit(visitId);
        return ResponseEntity.ok("Visita cancelada correctamente");
    }
} 