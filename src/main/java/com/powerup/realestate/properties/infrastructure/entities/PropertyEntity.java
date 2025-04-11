package com.powerup.realestate.properties.infrastructure.entities;

import com.powerup.realestate.location.infrastructure.entities.LocationEntity;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String address;
        private String description;
        private int rooms;
        private int bathrooms;
        private BigDecimal price;
        private LocalDate activePublicationDate;
        @Enumerated(EnumType.STRING)
        private PublicationStatus publicationStatus;
        private LocalDate publicationDate;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
        private CategoryEntity category;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "location_id", nullable = false, referencedColumnName = "id")
        private LocationEntity location;


    }

