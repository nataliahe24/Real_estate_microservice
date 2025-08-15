package com.powerup.realestate.properties.infrastructure.endpoints.rest;

import com.powerup.realestate.properties.application.dto.request.ScheduleBuyerVisitRequest;
import com.powerup.realestate.properties.application.dto.response.SaveScheduleBuyerResponse;
import com.powerup.realestate.properties.application.dto.response.ScheduleBuyerVisitResponse;
import com.powerup.realestate.properties.application.dto.response.SellerBuyerVisitResponse;
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
    public ResponseEntity<SaveScheduleBuyerResponse> scheduleBuyerVisit(
            @Valid @RequestBody ScheduleBuyerVisitRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(buyerVisitService.scheduleBuyerVisit(request));
    }
    
    @GetMapping("/seller/{sellerId}")
    @Operation(
            summary = "Obtener visitas agendadas por vendedor",
            description = "Lista todas las visitas agendadas para propiedades del vendedor especificado",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<List<SellerBuyerVisitResponse>> getBuyerVisitsBySellerId(
            @PathVariable Long sellerId) {
        return ResponseEntity.ok(buyerVisitService.getBuyerVisitsBySellerId(sellerId));
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