package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
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
    private final Long validLocation = 10L;
    private final Long validCategory = 20L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        propertyModel = new PropertyModel(
                1L,
                "Casa Familiar",
                "Bonita casa con jardín",
                validCategory,
                3,
                2,
                new BigDecimal("2500000"),
                validLocation,
                LocalDate.of(2025, 4, 7),
                PublicationStatus.PUBLISHED,
                LocalDate.of(2025, 4, 8)
        );
    }
    @Test
    void saveProperty_validInput_callsPersistence() {


        when(locationPersistencePort.findByLocationId(validLocation)).thenReturn(mock(LocationModel.class));
        when(categoryPersistencePort.getCategoryById(validCategory)).thenReturn(mock(CategoryModel.class));
        doNothing().when(propertyPersistencePort).save(any());

        propertyUseCase.save(propertyModel);

        verify(locationPersistencePort, times(1)).findByLocationId(validLocation);
        verify(categoryPersistencePort, times(1)).getCategoryById(validCategory);
        verify(propertyPersistencePort, times(1)).save(propertyModel);
    }

    @Test
    void saveProperty_nonExistingLocation_throwsLocationNotFoundException() {
        when(locationPersistencePort.findByLocationId(validLocation)).thenReturn(null);

        assertThrows(LocationNotFoundException.class, () -> propertyUseCase.save(propertyModel));

        verify(locationPersistencePort, times(1)).findByLocationId(validLocation);
        verify(categoryPersistencePort, never()).getCategoryById(any());
        verify(propertyPersistencePort, never()).save(any());
    }

    @Test
    void saveProperty_nonExistingCategory_throwsCategoryNotFoundException() {
        when(locationPersistencePort.findByLocationId(validLocation)).thenReturn(mock(LocationModel.class));
        when(categoryPersistencePort.getCategoryById(validCategory)).thenReturn(null);

        assertThrows(CategoryNotFoundException.class, () -> propertyUseCase.save(propertyModel));

        verify(locationPersistencePort, times(1)).findByLocationId(validLocation);
        verify(categoryPersistencePort, times(1)).getCategoryById(validCategory);
        verify(propertyPersistencePort, never()).save(any());
    }

}

