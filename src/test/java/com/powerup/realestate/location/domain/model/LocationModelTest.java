package com.powerup.realestate.location.domain.model;

import com.powerup.realestate.properties.domain.utils.constants.LocationDomainConstants;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;
import com.powerup.realestate.properties.infrastructure.entities.DepartmentEntity;
import com.powerup.realestate.properties.infrastructure.entities.LocationEntity;
import com.powerup.realestate.properties.domain.model.LocationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


class LocationModelTest {

    private  Long id;
    private CityEntity cityName;
    private  String neighborhood;
    DepartmentEntity department = new DepartmentEntity(1L, "Nombre del Departamento", "Descripción del Departamento", null);
    List<LocationEntity> locations = new ArrayList<>();



    @BeforeEach
    void setUp() {
        id = 1L;
        cityName = new CityEntity(1L, "Cúcuta", "Ciudad principal", department, locations);
        neighborhood = "Niza";


    }


    @Test
    void shouldCreateLocationSuccessfully() {
        LocationModel location = new LocationModel(1L, cityName, "La Ceiba");
        assertNotNull(location);
        assertEquals(1L, location.getId());
        assertEquals(cityName, location.getCityName());
        assertEquals("Cúcuta", location.getCityName().getName());
        assertEquals(department, location.getCityName().getDepartmentEntity());
        assertEquals("La Ceiba", location.getNeighborhood());
    }

    @Test
    void shouldThrowExceptionWhenCityIsNull() {
        LocationModel location = new LocationModel(id, cityName, neighborhood);

        NullPointerException exception = assertThrows(NullPointerException.class,() -> location.setCityName(null));

        assertEquals(LocationDomainConstants.FIELD_CITY_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionNeighborhoodIsNull() {
        LocationModel location = new LocationModel(id, cityName, neighborhood);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> location.setNeighborhood(null));

        assertEquals(LocationDomainConstants.FIELD_NEIGHBORHOOD_NULL_MESSAGE, exception.getMessage());
    }

}