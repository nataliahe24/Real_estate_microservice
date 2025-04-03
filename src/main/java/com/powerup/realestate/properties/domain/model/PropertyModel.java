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

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private final int rooms;
    private final int bathrooms;
    private BigDecimal price;
    private Long locationId;
    private LocalDate activePublicationDate;
    private PublicationStatus publicationStatus;
    private LocalDate publicationDate;


    public PropertyModel(Long id, String name, String description, Long categoryId,
                         int rooms, int bathrooms, BigDecimal price, Long locationId,
                         LocalDate activePublicationDate, PublicationStatus publicationStatus, LocalDate publicationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.price = price;
        this.locationId = locationId;
        this.activePublicationDate = activePublicationDate;
        this.publicationStatus = publicationStatus;
        this.publicationDate = publicationDate;


        if (rooms < 0) throw new InvalidRoomsException();
        if (bathrooms < 0) throw new InvalidBathroomsException();
        if (activePublicationDate != null &&  activePublicationDate.isAfter(LocalDate.now().plusMonths(1))) throw new InvalidActivePublicationDateException();

    }

        public void setName(String name) {
            this.name = Objects.requireNonNull(name, FIELD_NAME_NULL_MESSAGE);
        }

        public void setDescription(String description) {
            this.description = Objects.requireNonNull(description, FIELD_DESCRIPTION_NULL_MESSAGE);
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = Objects.requireNonNull(categoryId, FIELD_CATEGORY_NULL_MESSAGE);
        }

        public void setPrice(BigDecimal price) {
            this.price = Objects.requireNonNull(price, FIELD_PRICE_NULL_MESSAGE);
        }

        public void setLocationId(Long locationId) {
            this.locationId = Objects.requireNonNull(locationId, FIELD_LOCATION_NULL_MESSAGE);
        }

        public void setActivePublicationDate(LocalDate activePublicationDate) {
            if (activePublicationDate.isAfter(LocalDate.now().plusMonths(1))) throw new InvalidActivePublicationDateException();
            this.activePublicationDate = Objects.requireNonNull(activePublicationDate, FIELD_ACTIVE_PUBLICATION_DATE_NULL_MESSAGE);
        }

        public void setPublicationStatus(PublicationStatus publicationStatus) {
            this.publicationStatus = Objects.requireNonNull(publicationStatus, FIELD_PUBLICATION_STATUS_NULL_MESSAGE);
        }

        public void setPublicationDate(LocalDate publicationDate) {
            this.publicationDate = Objects.requireNonNull(publicationDate, FIELD_PUBLICATION_DATE_NULL_MESSAGE);
        }

}
