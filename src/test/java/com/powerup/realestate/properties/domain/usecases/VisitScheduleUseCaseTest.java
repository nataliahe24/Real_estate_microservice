package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.UnauthorizedSellerException;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VisitScheduleUseCaseTest {

    @Mock
    private VisitSchedulePersistencePort visitSchedulePersistencePort;
    
    @Mock
    private PropertyPersistencePort propertyPersistencePort;
    
    @InjectMocks
    private VisitScheduleUseCase visitScheduleUseCase;
    
    private PropertyModel property;
    private VisitScheduleModel visitSchedule;
    private Long sellerId = 1L;
    private Long propertyId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        

        CityEntity cityMock = Mockito.mock(CityEntity.class);
        when(cityMock.getId()).thenReturn(1L);
        when(cityMock.getName()).thenReturn("TestCity");
        

        LocationModel location = new LocationModel(1L, cityMock, "Centro");
        

        CategoryModel category = new CategoryModel(1L, "Casa", "Residencial");
        

        property = new PropertyModel(
            propertyId,
            "Casa Bonita",
            "Calle Principal 123",
            "Hermosa casa en buen barrio",
            category,
            3,
            2,
            new BigDecimal("250000"),
            location,
            LocalDate.now(),
            null,
            LocalDate.now(),
            sellerId
        );
        

        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = startDate.plusHours(2);
        visitSchedule = new VisitScheduleModel(
            null,
            sellerId,
            property,
            startDate,
            endDate,
            0
        );
        

        when(propertyPersistencePort.findById(propertyId)).thenReturn(Optional.of(property));
        
        List<VisitScheduleModel> schedulesByProperty = Collections.singletonList(visitSchedule);
        when(visitSchedulePersistencePort.findByPropertyId(propertyId)).thenReturn(schedulesByProperty);
        
        List<VisitScheduleModel> schedulesBySeller = Collections.singletonList(visitSchedule);
        when(visitSchedulePersistencePort.findBySellerId(sellerId)).thenReturn(schedulesBySeller);
        
        PageResult<VisitScheduleModel> pageResult = new PageResult<>(
            Collections.singletonList(visitSchedule),
            0,
            10,
            1
        );
        when(visitSchedulePersistencePort.getSchedules(0, 10)).thenReturn(pageResult);
        when(visitSchedulePersistencePort.getFilteredSchedules(any(), any(), any(), anyInt(), anyInt()))
            .thenReturn(pageResult);
    }

    @Test
    void createSchedule_validData_shouldSaveAndReturnSchedule() {

        VisitScheduleModel result = visitScheduleUseCase.createSchedule(visitSchedule);
        

        assertNotNull(result);
        verify(propertyPersistencePort).findById(property.getId());
        verify(visitSchedulePersistencePort).save(visitSchedule);
    }
    
    @Test
    void createSchedule_nonExistingProperty_shouldThrowPropertyNotFoundException() {

        when(propertyPersistencePort.findById(propertyId)).thenReturn(Optional.empty());
        

        assertThrows(PropertyNotFoundException.class, () -> visitScheduleUseCase.createSchedule(visitSchedule));
        verify(visitSchedulePersistencePort, never()).save(any());
    }
    
    @Test
    void createSchedule_unauthorizedSeller_shouldThrowUnauthorizedSellerException() {

        Long differentSellerId = 999L;
        PropertyModel propertyWithDifferentSeller = new PropertyModel(
            propertyId,
            "Casa Bonita",
            "Calle Principal 123",
            "Hermosa casa en buen barrio",
            new CategoryModel(1L, "Casa", "Residencial"),
            3,
            2,
            new BigDecimal("250000"),
            new LocationModel(1L, Mockito.mock(CityEntity.class), "Centro"),
            LocalDate.now(),
            null,
            LocalDate.now(),
            differentSellerId
        );
        
        when(propertyPersistencePort.findById(propertyId)).thenReturn(Optional.of(propertyWithDifferentSeller));
        

        assertThrows(UnauthorizedSellerException.class, () -> visitScheduleUseCase.createSchedule(visitSchedule));
        verify(visitSchedulePersistencePort, never()).save(any());
    }
    
    @Test
    void getSchedulesByPropertyId_shouldReturnSchedulesForProperty() {

        List<VisitScheduleModel> result = visitScheduleUseCase.getSchedulesByPropertyId(propertyId);
        

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(visitSchedulePersistencePort).findByPropertyId(propertyId);
    }
    
    @Test
    void getSchedulesBySellerId_shouldReturnSchedulesForSeller() {

        List<VisitScheduleModel> result = visitScheduleUseCase.getSchedulesBySellerId(sellerId);
        

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(visitSchedulePersistencePort).findBySellerId(sellerId);
    }
    
    @Test
    void getSchedules_shouldReturnPaginatedSchedules() {

        PageResult<VisitScheduleModel> result = visitScheduleUseCase.getSchedules(0, 10);
        

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getSize());
        verify(visitSchedulePersistencePort).getSchedules(0, 10);
    }

} 