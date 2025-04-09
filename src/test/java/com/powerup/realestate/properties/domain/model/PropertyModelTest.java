package com.powerup.realestate.properties.domain.model;


import com.powerup.realestate.location.domain.model.LocationModel;
import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import com.powerup.realestate.location.infrastructure.entities.DepartmentEntity;
import com.powerup.realestate.properties.domain.exceptions.InvalidActivePublicationDateException;
import com.powerup.realestate.properties.domain.exceptions.InvalidBathroomsException;
import com.powerup.realestate.properties.domain.exceptions.InvalidRoomsException;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.domain.utils.constants.PropertyDomainContants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

class PropertyModelTest {

    private Long id;
    private String name;
    private String description;
    private CategoryModel category;
    private int rooms;
    private int bathrooms;
    private BigDecimal price;
    private LocationModel location;
    private LocalDate activePublicationDate;
    private PublicationStatus publicationStatus;
    private LocalDate publicationDate;

    DepartmentEntity department = DepartmentEntity.builder()
            .id(1L)
            .name("Norte de Santander")
            .build();

    CityEntity city = CityEntity.builder()
            .id(1L)
            .name("Cucuta")
            .departmentEntity(department)
            .build();

    @BeforeEach
    void setUp() {
        id = 1L;
        name = "Beautiful House";
        description = "A spacious and modern house";
        category = CategoryModel.builder()
                .id(1L)
                .name("House")
                .description("Residential house")
                .build();
        rooms = 3;
        bathrooms = 2;
        price = BigDecimal.valueOf(250000);
        location = LocationModel.builder()
                .id(2L)
                .neighborhood("Downtown")
                .cityName(city)
                .build();
        activePublicationDate = LocalDate.now();
        publicationStatus = PublicationStatus.PUBLISHED;
        publicationDate = LocalDate.now();
    }

    @Test
    void shouldCreatePropertySuccessfully() {
        PropertyModel property =
                new PropertyModel(id, name, description, category, rooms, bathrooms,
                        price, location, activePublicationDate, publicationStatus, publicationDate);

        assertEquals(name, property.getName());
        assertEquals(description, property.getDescription());
        assertEquals(category, property.getCategory());
        assertEquals(rooms, property.getRooms());
        assertEquals(bathrooms, property.getBathrooms());
        assertEquals(price, property.getPrice());
        assertEquals(location, property.getLocation());
        assertEquals(activePublicationDate, property.getActivePublicationDate());
        assertEquals(publicationDate, LocalDate.now());
    }
    @Test
    void shouldThrowExceptionWhenRoomsAreNegative() {
        assertThrows(InvalidRoomsException.class, () ->
                new PropertyModel(id, name, description, category, -1,
                        bathrooms, price, location, activePublicationDate,
                        publicationStatus, publicationDate));
    }

    @Test
    void shouldThrowExceptionWhenBathroomsAreNegative() {
        assertThrows(InvalidBathroomsException.class, () ->
                new PropertyModel(id, name, description, category, rooms, -1,
                        price, location, activePublicationDate,
                        publicationStatus, publicationDate));
    }

    @Test
    void shouldThrowExceptionWhenActivePublicationDateIsTooFarInFuture() {
        LocalDate futureDate = LocalDate.now().plusMonths(2);
        assertThrows(InvalidActivePublicationDateException.class, () ->
                new PropertyModel(id, name, description, category, rooms, bathrooms, price, location,
                        futureDate, publicationStatus, publicationDate));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setName(null));
        assertEquals(PropertyDomainContants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setDescription(null));
        assertEquals(PropertyDomainContants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setPrice(null));
        assertEquals(PropertyDomainContants.FIELD_PRICE_NULL_MESSAGE, exception.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenPublicationDateIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setPublicationDate(null));
        assertEquals(PropertyDomainContants.FIELD_PUBLICATION_DATE_NULL_MESSAGE, exception.getMessage());
    }

}
