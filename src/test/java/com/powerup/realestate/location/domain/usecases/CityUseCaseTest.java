package com.powerup.realestate.location.domain.usecases;

import com.powerup.realestate.location.domain.exceptions.CityNonExistentException;

import com.powerup.realestate.location.domain.ports.in.CityServicePort;
import com.powerup.realestate.location.domain.ports.out.CityPersistencePort;

import com.powerup.realestate.location.infrastructure.entities.CityEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CityUseCaseTest {

    @Mock
    private CityPersistencePort cityPersistencePort;
    @Mock
    private CityServicePort cityServicePort;

    @InjectMocks
    private CityUseCase cityUseCase;

    @BeforeEach
    void setUp() {
        cityPersistencePort = mock(CityPersistencePort.class);
        cityUseCase = new CityUseCase(cityPersistencePort);
    }

    @Test
    void findCityByName_ShouldReturnCity_WhenCityExists() {

        CityEntity city = new CityEntity();
        city.setId(1L);
        city.setName("Bogotá");

        when(cityPersistencePort.findByNameIgnoreCaseAndTrim("Bogotá")).thenReturn(city);

        CityEntity result = cityUseCase.findCityByNameIgnoreCaseAndTrim("Bogotá");

        assertNotNull(result);
        assertEquals("Bogotá", result.getName());
    }

    @Test
    void findCityByName_ShouldThrowException_WhenCityNotFound() {
        when(cityPersistencePort.findByNameIgnoreCaseAndTrim("Ciudad Fantasma")).thenReturn(null);


        assertThrows(CityNonExistentException.class, () ->
                cityUseCase.findCityByNameIgnoreCaseAndTrim("Ciudad Fantasma")
        );
    }

    @Test
    void findCityByName_ShouldThrowException_WhenCityNameIsEmpty() {

        assertThrows(CityNonExistentException.class, () ->
                cityUseCase.findCityByNameIgnoreCaseAndTrim(" ")
        );
    }
}
