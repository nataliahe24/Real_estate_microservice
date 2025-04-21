package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
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
                "direction",
                "category",
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
        when(locationPersistencePort.findByLocationId(validLocationModel.getId()))
                .thenReturn(Optional.of(validLocationModel));
        when(categoryPersistencePort.getCategoryById(validCategoryModel.getId()))
                .thenReturn(Optional.of(validCategoryModel));
    }

    @Test
    void savePropertyProperty_validInput_savesSuccessfully() {
        // Act
        propertyUseCase.saveProperty(propertyModel);

        // Assert
        verify(locationPersistencePort, times(1)).findByLocationId(propertyModel.getLocation().getId());
        verify(categoryPersistencePort, times(1)).getCategoryById(propertyModel.getCategory().getId());
        verify(propertyPersistencePort, times(1)).save(propertyModel);
    }

    @Test
    void savePropertyProperty_nonExistingLocation_throwsLocationNotFoundException() {
        when(locationPersistencePort.findByLocationId(propertyModel.getLocation().getId()))
                .thenReturn(Optional.empty());

        assertThrows(LocationNotFoundException.class, () -> propertyUseCase.saveProperty(propertyModel));

        verify(propertyPersistencePort, never()).save(any());
    }

    @Test
    void savePropertyProperty_nonExistingCategory_throwsCategoryNotFoundException() {
        when(categoryPersistencePort.getCategoryById(propertyModel.getCategory().getId()))
                .thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> propertyUseCase.saveProperty(propertyModel));

        verify(propertyPersistencePort, never()).save(any());
    }
}

