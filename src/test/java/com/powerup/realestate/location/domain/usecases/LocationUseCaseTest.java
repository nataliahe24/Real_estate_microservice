package com.powerup.realestate.location.domain.usecases;

import com.powerup.realestate.location.domain.exceptions.DepartmentAlreadyExistsException;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationUseCaseTest {

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @InjectMocks
    private LocationUseCase locationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveLocation_WhenDepartmentDoesNotExist() {

        LocationModel locationModel = new LocationModel(
                1L,
                "City",
                "Description City",
                "New Department",
                "Description Department"
        );

        when(locationPersistencePort.existsByDepartment("New Department")).thenReturn(false);

        assertDoesNotThrow(() -> locationUseCase.save(locationModel));

        verify(locationPersistencePort, times(1)).save(locationModel);

    }
    @Test
    void save_ShouldThrowException_WhenDepartmentExists() {

        LocationModel locationModel = new LocationModel(
                2L,
                "City",
                "Description City",
                "Existing Department",
                "Description Department"
                );
        when(locationPersistencePort.existsByDepartment("Existing Department")).thenReturn(true);

        assertThrows(DepartmentAlreadyExistsException.class, () -> locationUseCase.save(locationModel));

        verify(locationPersistencePort, never()).save(locationModel);
    }
}