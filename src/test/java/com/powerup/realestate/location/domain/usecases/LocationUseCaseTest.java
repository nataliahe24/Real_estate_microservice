package com.powerup.realestate.location.domain.usecases;

import com.powerup.realestate.location.domain.exceptions.DepartmentAlreadyExistsException;
import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.location.domain.utils.constants.page.PageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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

    @Test
    void getLocations_ShouldReturnSortedList_WhenOrderIsAsc() {
        LocationModel locationModel1 = new LocationModel(
                1L,
                "City1",
                "Description City1",
                "Department1",
                "Description Department1"
        );
        LocationModel locationModel2 = new LocationModel(
                2L,
                "City2",
                "Description City2",
                "Department2",
                "Description Department2"
        );

        String searchText = "city or department";
        Integer page = 1;
        Integer size = 2;
        int totalElements = 3;
        boolean orderAsc = true;
        List<LocationModel> locationPaginationMock = List.of(locationModel1, locationModel2);
        PageResult<LocationModel> pageResultMock = new PageResult<>(locationPaginationMock, page, size, totalElements);

        when(locationPersistencePort.getLocations(searchText, page, size, orderAsc)).thenReturn(pageResultMock);

        PageResult<LocationModel> paginatedLocationList = locationUseCase.getLocations(searchText, page, size, orderAsc);

        verify(locationPersistencePort).getLocations(searchText, page, size, orderAsc);
        assertEquals(pageResultMock, paginatedLocationList);
    }
}