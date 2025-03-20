package com.powerup.realestate.location.domain.usecases;

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