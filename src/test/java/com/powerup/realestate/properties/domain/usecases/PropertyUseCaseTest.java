package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.application.services.PropertyValidationService;
import com.powerup.realestate.properties.domain.exceptions.CategoryNotFoundException;
import com.powerup.realestate.properties.domain.exceptions.LocationNotFoundException;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PropertyUseCaseTest {

    @Mock
    private PropertyPersistencePort propertyPersistencePort;

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @Mock
    private PropertyValidationService propertyValidationService;

    @InjectMocks
    private PropertyUseCase propertyUseCase;
    private PropertyModel propertyModel;
    private LocationModel validLocationModel;
    private CategoryModel validCategoryModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validCategoryModel = CategoryModel.builder().id(20L).name("Casa").description("Casa familiar").build();
        validLocationModel = LocationModel.builder().id(10L).neighborhood("Centro").build();

        propertyModel = new PropertyModel(
                1L,
                "Casa Familiar",
                "Bonita casa con jardín",
                validCategoryModel,
                3,
                2,
                new BigDecimal("2500000"),
                validLocationModel,
                LocalDate.of(2025, 4, 7),
                PublicationStatus.PUBLISHED,
                LocalDate.of(2025, 4, 8)
        );

        // Configura el comportamiento del mock locationPersistencePort.
        when(locationPersistencePort.findByLocationId(validLocationModel.getId())).thenReturn(Optional.ofNullable(validLocationModel));
        when(categoryPersistencePort.getCategoryById(validCategoryModel.getId())).thenReturn(Optional.ofNullable(validCategoryModel));
        doNothing().when(propertyPersistencePort).save(any());
        doNothing().when(propertyValidationService).validate(any());
    }

    @Test
    void saveProperty_validInput_callsPersistenceAndValidation() {
        propertyUseCase.save(propertyModel);

        verify(propertyValidationService, times(1)).validate(propertyModel);
        verify(propertyPersistencePort, times(1)).save(propertyModel);
    }

    @Test
    void saveProperty_nonExistingLocation_throwsLocationNotFoundException() {
        when(locationPersistencePort.findByLocationId(validLocationModel.getId())).thenReturn(null);
        doThrow(new LocationNotFoundException())
                .when(propertyValidationService).validate(propertyModel);

        assertThrows(LocationNotFoundException.class, () -> propertyUseCase.save(propertyModel));

        verify(propertyPersistencePort, never()).save(any());
        verify(propertyValidationService, times(1)).validate(propertyModel);
    }

    @Test
    void saveProperty_nonExistingCategory_throwsCategoryNotFoundException() {
        when(categoryPersistencePort.getCategoryById(validCategoryModel.getId())).thenReturn(null);
        doThrow(new CategoryNotFoundException())
                .when(propertyValidationService).validate(propertyModel);

        assertThrows(CategoryNotFoundException.class, () -> propertyUseCase.save(propertyModel));

        verify(propertyPersistencePort, never()).save(any());
        verify(propertyValidationService, times(1)).validate(propertyModel);
    }
}

