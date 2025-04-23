package com.powerup.realestate.properties.domain.model;

import com.powerup.realestate.properties.domain.exceptions.InvalidActivePublicationDateException;
import com.powerup.realestate.properties.domain.exceptions.InvalidBathroomsException;
import com.powerup.realestate.properties.domain.exceptions.InvalidRoomsException;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.domain.utils.constants.PropertyDomainContants;
import com.powerup.realestate.properties.infrastructure.entities.CityEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PropertyModelTest {

    private Long id;
    private String name;
    private String address;
    private String description;
    private CategoryModel category;
    private int rooms;
    private int bathrooms;
    private BigDecimal price;
    private LocationModel location;
    private LocalDate activePublicationDate;
    private PublicationStatus publicationStatus;
    private LocalDate publicationDate;
    private Long sellerId;

    @BeforeEach
    void setUp() {
        id = 1L;
        name = "Beautiful House";
        address = "address";
        description = "A spacious and modern house";
        category = new CategoryModel(1L, "House", "Residential house");
        rooms = 3;
        bathrooms = 2;
        price = BigDecimal.valueOf(250000);

        CityEntity cityMock = Mockito.mock(CityEntity.class);
        when(cityMock.getId()).thenReturn(1L);
        when(cityMock.getName()).thenReturn("TestCity");
        location = new LocationModel(2L, cityMock, "Downtown");
        
        activePublicationDate = LocalDate.now();
        publicationStatus = PublicationStatus.PUBLISHED;
        publicationDate = LocalDate.now();
        sellerId = 1L;
    }

    @Test
    void shouldCreatePropertySuccessfully() {
        PropertyModel property =
                new PropertyModel(id, name, address, description, category, rooms, bathrooms,
                        price, location, activePublicationDate, publicationStatus, publicationDate, sellerId);

        assertEquals(id, property.getId());
        assertEquals(name, property.getName());
        assertEquals(address, property.getAddress());
        assertEquals(description, property.getDescription());
        assertEquals(category, property.getCategory());
        assertEquals(rooms, property.getRooms());
        assertEquals(bathrooms, property.getBathrooms());
        assertEquals(price, property.getPrice());
        assertEquals(location, property.getLocation());
        assertEquals(activePublicationDate, property.getActivePublicationDate());
        assertEquals(publicationStatus, property.getPublicationStatus());
        assertEquals(publicationDate, property.getPublicationDate());
        assertEquals(sellerId, property.getSellerId());
    }

    @Test
    void shouldThrowExceptionWhenRoomsAreNegative() {
        assertThrows(InvalidRoomsException.class, () ->
                new PropertyModel(id, name, address, description, category, -1,
                        bathrooms, price, location, activePublicationDate,
                        publicationStatus, publicationDate, sellerId));
    }

    @Test
    void shouldThrowExceptionWhenBathroomsAreNegative() {
        assertThrows(InvalidBathroomsException.class, () ->
                new PropertyModel(id, name, address, description, category, rooms, -1,
                        price, location, activePublicationDate,
                        publicationStatus, publicationDate, sellerId));
    }

    @Test
    void shouldThrowExceptionWhenActivePublicationDateIsTooFarInFuture() {
        LocalDate futureDate = LocalDate.now().plusMonths(2);
        assertThrows(InvalidActivePublicationDateException.class, () ->
                new PropertyModel(id, name, address, description, category, rooms, bathrooms, price, location,
                        futureDate, publicationStatus, publicationDate, sellerId));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        PropertyModel property = new PropertyModel(id, name, address, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate, sellerId);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setName(null));
        assertEquals(PropertyDomainContants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        PropertyModel property = new PropertyModel(id, name, address, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate, sellerId);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setDescription(null));
        assertEquals(PropertyDomainContants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        PropertyModel property = new PropertyModel(id, name, address, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate, sellerId);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setPrice(null));
        assertEquals(PropertyDomainContants.FIELD_PRICE_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSellerIdIsNull() {
        PropertyModel property = new PropertyModel(id, name, address, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate, sellerId);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setSellerId(null));
        assertEquals(PropertyDomainContants.FIELD_SELLER_ID_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenActivePublicationDateIsNull() {
        PropertyModel property = new PropertyModel(id, name, address, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate, sellerId);
        
        NullPointerException exception = assertThrows(NullPointerException.class, 
                () -> property.setActivePublicationDate(null));
        
        assertEquals(PropertyDomainContants.FIELD_ACTIVE_PUBLICATION_DATE_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAddressIsNull() {
        PropertyModel property = new PropertyModel(id, name, address, description, category, rooms,
                bathrooms, price, location, activePublicationDate, publicationStatus, publicationDate, sellerId);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> property.setAddress(null));
        assertEquals(PropertyDomainContants.FIELD_ADDRESS_NULL_MESSAGE, exception.getMessage());
    }
}
