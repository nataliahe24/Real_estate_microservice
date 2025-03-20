package com.powerup.realestate.location.domain.model;

import com.powerup.realestate.location.domain.exceptions.CityMaxSizeExceededException;
import com.powerup.realestate.location.domain.exceptions.DepartmentMaxSizeExceededException;
import com.powerup.realestate.location.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.location.domain.utils.constants.LocationDomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;


class LocationModelTest {

    private Long id;
    private String city;
    private String descriptionCity;
    private String department;
    private String descriptionDepartment;

    @BeforeEach
    void setUp() {
        id = 1L;
        city = "City";
        descriptionCity = "Description City";
        department = "Department";
        descriptionDepartment = "Description Department";

    }


    @Test
    void shouldCreateLocationSuccessfully() {

        LocationModel location = new LocationModel(id, city, descriptionCity, department, descriptionDepartment);

        assertEquals(id, location.getId());
        assertEquals(city, location.getCity());
        assertEquals(descriptionCity, location.getDescriptionCity());
        assertEquals(department, location.getDepartment());
        assertEquals(descriptionDepartment, location.getDescriptionDepartment());

    }
    @Test
    void shouldCreateLocationWhenCityHas50CharactersOrLess() {
        String validCity = "C".repeat(50);

        assertDoesNotThrow( () -> new LocationModel(id, validCity,descriptionCity, department, descriptionDepartment));

    }
    @Test
    void shouldCreateLocationWhenDepartmentHas50CharactersOrLess() {
        String validDepartment = "D".repeat(50);

        assertDoesNotThrow( () -> new LocationModel(id, city,descriptionCity, validDepartment, descriptionDepartment));

    }
    @Test
    void shouldCreateLocationWhenDescriptionCityHas120CharactersOrLess() {
        String validDescriptionCity = "D".repeat(120);

        assertDoesNotThrow( () -> new LocationModel(id, city, validDescriptionCity, department, descriptionDepartment));

    }
    @Test
    void shouldCreateLocationWhenDescriptionDepartmentHas120CharactersOrLess() {
        String validDescriptionDepartment = "D".repeat(120);

        assertDoesNotThrow( () -> new LocationModel(id, city,descriptionCity, department, validDescriptionDepartment));

    }
    @Test
    void shouldThrowExceptionWhenCityExceeds50Characters() {
        String invalidCity = "C".repeat(51);

        assertThrows(CityMaxSizeExceededException.class, () -> new LocationModel(id, invalidCity,descriptionCity, department, descriptionDepartment));
    }
    @Test
    void shouldThrowExceptionWhenDepartmentExceeds50Characters() {
        String invalidDepartment = "D".repeat(51);

        assertThrows(DepartmentMaxSizeExceededException.class, () -> new LocationModel(id, city,descriptionCity, invalidDepartment, descriptionDepartment));
    }
    @Test
    void shouldThrowExceptionWhenDescriptionCityExceeds120Characters() {
        String invalidDescriptionCity = "D".repeat(121);

        assertThrows(DescriptionMaxSizeExceededException.class, () -> new LocationModel(id, city,invalidDescriptionCity, department, descriptionDepartment));
    }
    @Test
    void shouldThrowExceptionWhenDescriptionDepartmentExceeds120Characters() {
        String invalidDescriptionDepartment = "D".repeat(121);

        assertThrows(DescriptionMaxSizeExceededException.class, () -> new LocationModel(id, city,descriptionCity, department, invalidDescriptionDepartment));
    }
    @Test
    void shouldThrowExceptionWhenCityIsNull() {
        LocationModel location = new LocationModel(id, city, descriptionCity, department, descriptionDepartment);

        NullPointerException exception = assertThrows(NullPointerException.class,() -> location.setCity(null));

        assertEquals(LocationDomainConstants.FIELD_CITY_NULL_MESSAGE, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() {
        LocationModel location = new LocationModel(id, city, descriptionCity, department, descriptionDepartment);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> location.setDepartment(null));

        assertEquals(LocationDomainConstants.FIELD_DEPARTMENT_NULL_MESSAGE, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenDescriptionCityIsNull() {
        LocationModel location = new LocationModel(id, city, descriptionCity, department, descriptionDepartment);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> location.setDescriptionCity(null));

        assertEquals(LocationDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenDescriptionDepartmentIsNull() {
        LocationModel location = new LocationModel(id, city, descriptionCity, department, descriptionDepartment);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> location.setDescriptionDepartment(null));

        assertEquals(LocationDomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }
}