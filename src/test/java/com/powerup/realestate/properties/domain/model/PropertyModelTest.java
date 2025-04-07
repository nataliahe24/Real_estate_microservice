package com.powerup.realestate.properties.domain.model;

import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import com.powerup.realestate.location.infrastructure.entities.DepartmentEntity;
import com.powerup.realestate.location.infrastructure.entities.LocationEntity;
import com.powerup.realestate.properties.domain.exceptions.InvalidActivePublicationDateException;
import com.powerup.realestate.properties.domain.exceptions.InvalidBathroomsException;
import com.powerup.realestate.properties.domain.exceptions.InvalidRoomsException;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.domain.utils.constants.PropertyDomainContants;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyModelTest {

    private Long id;
    private String name;
    private String description;
    private CategoryEntity categoryId;
    private int rooms;
    private int bathrooms;
    private BigDecimal price;
    private LocationEntity locationId;
    private LocalDate activePublicationDate;
    private PublicationStatus publicationStatus;
    private LocalDate publicationDate;
    DepartmentEntity department = new DepartmentEntity(1L, "Nombre del Departamento", "Descripción del Departamento", null);
    List<LocationEntity> locations = new ArrayList<>();
    CityEntity city = new CityEntity(1L, "Cúcuta", "Ciudad principal", department, locations);

    @BeforeEach
    void setUp() {
        id = 1L;
        name = "Beautiful House";
        description = "A spacious and modern house";
        categoryId = new CategoryEntity(1L,"ciudad", "descripcion ciudad");
        rooms = 3;
        bathrooms = 2;
        price = BigDecimal.valueOf(250000);
        locationId = new LocationEntity(1L,"Ubicacion",city );
        activePublicationDate = LocalDate.now();
        publicationStatus = PublicationStatus.PUBLISHED;
        publicationDate = LocalDate.now();
    }

    @Test
    void shouldCreatePropertySuccessfully() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);

        assertEquals(name, property.getName());
        assertEquals(description, property.getDescription());
        assertEquals(categoryId, property.getCategoryId());
        assertEquals(rooms, property.getRooms());
        assertEquals(bathrooms, property.getBathrooms());
        assertEquals(price, property.getPrice());
        assertEquals(locationId, property.getLocationId());
        assertEquals(activePublicationDate, property.getActivePublicationDate());
        assertEquals(publicationStatus, property.getPublicationStatus());
        assertEquals(publicationDate, property.getPublicationDate());
    }
    @Test
    void shouldThrowExceptionWhenRoomsAreNegative() {
        assertThrows(InvalidRoomsException.class, () -> new PropertyModel(id, name, description, categoryId, -1, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate));
    }

    @Test
    void shouldThrowExceptionWhenBathroomsAreNegative() {
        assertThrows(InvalidBathroomsException.class, () -> new PropertyModel(id, name, description, categoryId, rooms, -1, price, locationId, activePublicationDate, publicationStatus, publicationDate));
    }

    @Test
    void shouldThrowExceptionWhenActivePublicationDateIsTooFarInFuture() {
        LocalDate futureDate = LocalDate.now().plusMonths(2);
        assertThrows(InvalidActivePublicationDateException.class, () -> new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, futureDate, publicationStatus, publicationDate));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setName(null));
        assertEquals(PropertyDomainContants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setDescription(null));
        assertEquals(PropertyDomainContants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryIdIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setCategoryId(null));
        assertEquals(PropertyDomainContants.FIELD_CATEGORY_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setPrice(null));
        assertEquals(PropertyDomainContants.FIELD_PRICE_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLocationIdIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setLocationId(null));
        assertEquals(PropertyDomainContants.FIELD_LOCATION_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPublicationStatusIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setPublicationStatus(null));
        assertEquals(PropertyDomainContants.FIELD_PUBLICATION_STATUS_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPublicationDateIsNull() {
        PropertyModel property = new PropertyModel(id, name, description, categoryId, rooms, bathrooms, price, locationId, activePublicationDate, publicationStatus, publicationDate);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setPublicationDate(null));
        assertEquals(PropertyDomainContants.FIELD_PUBLICATION_DATE_NULL_MESSAGE, exception.getMessage());
    }

}
