package com.powerup.realestate.location.domain.usecases;

import com.powerup.realestate.properties.domain.model.LocationModel;
import com.powerup.realestate.properties.domain.ports.in.CityServicePort;
import com.powerup.realestate.properties.domain.ports.out.LocationPersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import com.powerup.realestate.properties.infrastructure.entities.DepartmentEntity;
import com.powerup.realestate.properties.infrastructure.entities.LocationEntity;
import com.powerup.realestate.properties.domain.usecases.LocationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationUseCaseTest {

    @Mock
    private LocationPersistencePort locationPersistencePort;
    @Mock
    private CityServicePort cityServicePort;

    @InjectMocks
    private LocationUseCase locationUseCase;
    private LocationModel locationModel;
    private CityEntity cityEntity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        DepartmentEntity department = new DepartmentEntity(1L, "Nombre del Departamento", "Descripción del Departamento", null);
        List<LocationEntity> locations = new ArrayList<>();
        cityEntity = new CityEntity(1L, "Cúcuta", "Ciudad principal", department, locations); // Asignación al campo de clase
        locationModel = new LocationModel(null, cityEntity, "Mi Barrio"); // Inicialización de locationModel
    }

    @Test
    void saveLocationWithExistingCityName() {
        when(cityServicePort.findCityByNameIgnoreCaseAndTrim(cityEntity.getName()))
                .thenReturn(cityEntity);

        locationUseCase.save(locationModel);

        verify(cityServicePort, times(1))
                .findCityByNameIgnoreCaseAndTrim(cityEntity.getName());
        verify(locationPersistencePort, times(1))
                .save(argThat(model -> model.getCityName().getId().equals(cityEntity.getId())));
    }


    @Test
    void locationWithNonExistingCityNameShouldNotSave() {

        when(cityServicePort.findCityByNameIgnoreCaseAndTrim(cityEntity.getName()))
                .thenReturn(null);

        verify(locationPersistencePort, never()).save(any());
    }

    @Test
    void getLocations_ShouldReturnSortedList_WhenOrderIsAsc() {
        DepartmentEntity department = new DepartmentEntity(1L, "Nombre del Departamento", "Descripción del Departamento", null);
        List<LocationEntity> locations = new ArrayList<>();
        LocationModel locationModel1 = new LocationModel(
                1L,
                new CityEntity(1L, "Cúcuta", "Ciudad principal", department, locations),
                "Description City1"
        );
        LocationModel locationModel2 = new LocationModel(
                2L,
                new CityEntity(1L, "Cúcuta", "Ciudad principal", department, locations),
                "neighborhood"
        );

        String searchText = "city or department";
        Integer page = 1;
        Integer size = 2;
        int totalElements = 2;
        boolean orderAsc = true;
        List<LocationModel> locationPaginationMock = List.of(locationModel1, locationModel2);
        PageResult<LocationModel> pageResultMock = new PageResult<>(locationPaginationMock, page, size, totalElements);

        when(locationPersistencePort.getLocations(searchText, page, size, orderAsc)).thenReturn(pageResultMock);

        PageResult<LocationModel> paginatedLocationList = locationUseCase.getLocations(searchText, page, size, orderAsc);

        verify(locationPersistencePort).getLocations(searchText, page, size, orderAsc);
        assertEquals(pageResultMock, paginatedLocationList);
    }
}