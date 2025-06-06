package com.powerup.realestate.properties.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuyerVisitModelTest {

    @Mock
    private PropertyModel property;
    
    @Mock
    private VisitScheduleModel visitSchedule;
    
    private BuyerVisitModel buyerVisit;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        startDate = LocalDateTime.now();
        endDate = startDate.plusHours(1);
        
        when(visitSchedule.getId()).thenReturn(1L);
        when(visitSchedule.getStartDate()).thenReturn(startDate);
        when(visitSchedule.getEndDate()).thenReturn(endDate);
            
        buyerVisit = new BuyerVisitModel(1L, visitSchedule, "test@example.com");
    }

    @Test
    void setVisitSchedule_Success() {
        VisitScheduleModel newSchedule = mock(VisitScheduleModel.class);
        buyerVisit.setVisitSchedule(newSchedule);
        
        assertEquals(newSchedule, buyerVisit.getVisitSchedule());
    }

    @Test
    void setVisitSchedule_Null_ThrowsException() {
        NullPointerException exception = assertThrows(NullPointerException.class, 
            () -> buyerVisit.setVisitSchedule(null));
        
        assertEquals("El ID del horario de visita no puede ser nulo", exception.getMessage());
    }

    @Test
    void setBuyerEmail_Success() {
        String newEmail = "new@example.com";
        buyerVisit.setBuyerEmail(newEmail);
        
        assertEquals(newEmail, buyerVisit.getBuyerEmail());
    }

    @Test
    void setBuyerEmail_Null_ThrowsException() {
        NullPointerException exception = assertThrows(NullPointerException.class, 
            () -> buyerVisit.setBuyerEmail(null));
        
        assertEquals("El email del comprador no puede ser nulo", exception.getMessage());
    }
} 