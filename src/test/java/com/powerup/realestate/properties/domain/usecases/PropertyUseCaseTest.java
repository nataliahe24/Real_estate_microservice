package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PropertyUseCaseTest {

    @Mock
    private PropertyPersistencePort propertyPersistencePort;

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private PropertyUseCase propertyUseCase;
    
    private PropertyModel propertyModel;
    private LocationModel validLocationModel;
    private CategoryModel validCategoryModel;
    private Long sellerId = 100L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Crear CategoryModel usando constructor directo
        validCategoryModel = new CategoryModel(20L, "Casa", "Casa familiar");
        
        // Crear CityEntity mock
        CityEntity cityMock = Mockito.mock(CityEntity.class);
        when(cityMock.getId()).thenReturn(1L);
        when(cityMock.getName()).thenReturn("TestCity");
        
        // Crear LocationModel usando constructor directo
        validLocationModel = new LocationModel(10L, cityMock, "Centro");

        // Crear PropertyModel con todos los parámetros requeridos, incluyendo sellerId
        propertyModel = new PropertyModel(
                1L,                        // id
                "Casa Familiar",           // name
                "Calle Principal 123",     // address
                "Hermosa casa con jardín", // description
                validCategoryModel,        // category
                3,                         // rooms
                2,                         // bathrooms
                new BigDecimal("250000"),  // price
                validLocationModel,        // location
                LocalDate.of(2025, 4, 7),  // activePublicationDate
                PublicationStatus.PUBLISHED, // publicationStatus
                LocalDate.of(2025, 4, 8),  // publicationDate
                sellerId                   // sellerId - parámetro que faltaba
        );

        // Configurar comportamiento de mocks
        when(locationPersistencePort.findByLocationId(10L))
                .thenReturn(Optional.of(validLocationModel));
        when(categoryPersistencePort.getCategoryById(20L))
                .thenReturn(Optional.of(validCategoryModel));
        when(propertyPersistencePort.findById(1L))
                .thenReturn(Optional.of(propertyModel));
        when(propertyPersistencePort.findBySellerId(sellerId))
                .thenReturn(Collections.singletonList(propertyModel));
    }

    @Test
    void savePropertyProperty_validInput_savesSuccessfully() {
        // Act
        propertyUseCase.saveProperty(propertyModel);

        // Assert
        verify(locationPersistencePort, times(1)).findByLocationId(10L);
        verify(categoryPersistencePort, times(1)).getCategoryById(20L);
        verify(propertyPersistencePort, times(1)).save(propertyModel);
    }

    @Test
    void savePropertyProperty_nonExistingLocation_throwsLocationNotFoundException() {
        when(locationPersistencePort.findByLocationId(10L))
                .thenReturn(Optional.empty());

        assertThrows(LocationNotFoundException.class, () -> propertyUseCase.saveProperty(propertyModel));

        verify(propertyPersistencePort, never()).save(any());
    }

    @Test
    void savePropertyProperty_nonExistingCategory_throwsCategoryNotFoundException() {
        when(categoryPersistencePort.getCategoryById(20L))
                .thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> propertyUseCase.saveProperty(propertyModel));

        verify(propertyPersistencePort, never()).save(any());
    }
    
    @Test
    void getPropertyById_existingId_returnsProperty() {
        // Act
        PropertyModel result = propertyUseCase.getPropertyById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(propertyModel, result);
    }
    
    @Test
    void getPropertyById_nonExistingId_throwsPropertyNotFoundException() {
        // Arrange
        when(propertyPersistencePort.findById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(PropertyNotFoundException.class, () -> propertyUseCase.getPropertyById(999L));
    }
    
    @Test
    void getPropertiesBySellerId_validSellerId_returnsListOfProperties() {
        // Act
        var result = propertyUseCase.getPropertiesBySellerId(sellerId);
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(propertyModel, result.get(0));
        verify(propertyPersistencePort).findBySellerId(sellerId);
    }
}

