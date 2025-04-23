package com.powerup.realestate.properties.domain.model;

import com.powerup.realestate.properties.domain.exceptions.InvalidVisitDateException;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static com.powerup.realestate.properties.domain.utils.constants.VisitScheduleDomainConstants.*;

class VisitScheduleModelTest {

    private final Long sellerId = 1L;
    private PropertyModel property;
    private CityEntity cityMock;

    @BeforeEach
    void setUp() {
        // Preparar CityEntity mock
        cityMock = Mockito.mock(CityEntity.class);
        when(cityMock.getId()).thenReturn(1L);
        when(cityMock.getName()).thenReturn("TestCity");
        
        // Crear LocationModel
        LocationModel location = new LocationModel(1L, cityMock, "Centro");
        
        // Crear CategoryModel
        CategoryModel category = new CategoryModel(1L, "Casa", "Residencial");
        
        // Crear PropertyModel
        property = new PropertyModel(
            1L,                            // id
            "Casa Bonita",                 // name
            "Calle Principal 123",         // address
            "Hermosa casa en buen barrio", // description
            category,                      // category
            3,                             // rooms
            2,                             // bathrooms
            new BigDecimal("250000"),      // price
            location,                      // location
            LocalDate.now(),               // activePublicationDate
            null,                          // publicationStatus
            LocalDate.now(),               // publicationDate
            sellerId                       // sellerId
        );
    }

    @Test
    void shouldCreateValidVisitSchedule() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusHours(2);
        
        // Act
        VisitScheduleModel visitSchedule = new VisitScheduleModel(
            null,       // id
            sellerId,   // sellerId
            property,   // property
            startDate,  // startDate
            endDate,    // endDate
            0           // scheduledBuyers
        );
        
        // Assert
        assertNotNull(visitSchedule);
        assertEquals(sellerId, visitSchedule.getSellerId());
        assertEquals(property, visitSchedule.getProperty());
        assertEquals(startDate, visitSchedule.getStartDate());
        assertEquals(endDate, visitSchedule.getEndDate());
        assertEquals(0, visitSchedule.getScheduledBuyers());
    }
    
    @Test
    void shouldThrowExceptionWhenSellerIdIsNull() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusHours(2);
        
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new VisitScheduleModel(null, null, property, startDate, endDate, 0);
        });
        
        assertEquals(FIELD_SELLER_ID_NULL_MESSAGE, exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenPropertyIsNull() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusHours(2);
        
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new VisitScheduleModel(null, sellerId, null, startDate, endDate, 0);
        });
        
        assertEquals(FIELD_PROPERTY_NULL_MESSAGE, exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenStartDateIsNull() {
        // Arrange
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);
        
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new VisitScheduleModel(null, sellerId, property, null, endDate, 0);
        });
        
        assertEquals(FIELD_START_DATE_NULL_MESSAGE, exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenEndDateIsNull() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        
        // Act & Assert
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new VisitScheduleModel(null, sellerId, property, startDate, null, 0);
        });
        
        assertEquals(FIELD_END_DATE_NULL_MESSAGE, exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenStartDateIsMoreThanThreeWeeksInFuture() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusWeeks(4);
        LocalDateTime endDate = startDate.plusHours(2);
        
        // Act & Assert
        assertThrows(InvalidVisitDateException.class, () -> {
            new VisitScheduleModel(null, sellerId, property, startDate, endDate, 0);
        });
    }
    
    @Test
    void shouldThrowExceptionWhenEndDateIsMoreThanThreeWeeksInFuture() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusWeeks(4);
        
        // Act & Assert
        assertThrows(InvalidVisitDateException.class, () -> {
            new VisitScheduleModel(null, sellerId, property, startDate, endDate, 0);
        });
    }
    
    @Test
    void shouldThrowExceptionWhenEndDateIsBeforeStartDate() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(2);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1); // Anterior a startDate
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new VisitScheduleModel(null, sellerId, property, startDate, endDate, 0);
        });
    }
    
    @Test
    void shouldSetDefaultScheduledBuyersWhenNull() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusHours(2);
        
        // Act
        VisitScheduleModel visitSchedule = new VisitScheduleModel(null, sellerId, property, startDate, endDate, null);
        
        // Assert
        assertEquals(0, visitSchedule.getScheduledBuyers());
    }
    
    @Test
    void shouldAllowToUpdateScheduledBuyers() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusHours(2);
        VisitScheduleModel visitSchedule = new VisitScheduleModel(null, sellerId, property, startDate, endDate, 0);
        
        // Act
        visitSchedule.setScheduledBuyers(1);
        
        // Assert
        assertEquals(1, visitSchedule.getScheduledBuyers());
    }
} 