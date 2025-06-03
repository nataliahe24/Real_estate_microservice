package com.powerup.realestate.properties.domain.model;

import com.powerup.realestate.properties.domain.exceptions.InvalidActivePublicationDateException;
import com.powerup.realestate.properties.domain.exceptions.InvalidBathroomsException;
import com.powerup.realestate.properties.domain.exceptions.InvalidRoomsException;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static com.powerup.realestate.properties.domain.utils.constants.PropertyDomainContants.*;

@Getter
@Builder
public class PropertyModel {

    @Setter
    private Long id;
    private String name;
    private String address;
    private String description;
    private CategoryModel category;
    private final int rooms;
    private final int bathrooms;
    private BigDecimal price;
    private LocationModel location;
    @Setter
    private LocalDate activePublicationDate;
    @Setter
    private PublicationStatus publicationStatus;
    @Setter
    private LocalDate publicationDate;
    private Long sellerId;


    public PropertyModel(Long id, String name, String address, String description, CategoryModel category,
                         int rooms, int bathrooms, BigDecimal price, LocationModel location,
                         LocalDate activePublicationDate, PublicationStatus publicationStatus,
                         LocalDate publicationDate, Long sellerId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.category = category;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.price = price;
        this.location = location;
        this.activePublicationDate = activePublicationDate;
        this.publicationStatus = publicationStatus;
        this.publicationDate = publicationDate;
        this.sellerId = sellerId;


        if (rooms < 0) throw new InvalidRoomsException();

        if (bathrooms < 0) throw new InvalidBathroomsException();

        if (activePublicationDate.isAfter(LocalDate.now().plusMonths(1)))
            throw new InvalidActivePublicationDateException();

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
