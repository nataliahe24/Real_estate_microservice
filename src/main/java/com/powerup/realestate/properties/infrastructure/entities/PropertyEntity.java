package com.powerup.realestate.properties.infrastructure.entities;

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
        private String description;
        private Long categoryId;
        private int rooms;
        private int bathrooms;
        private BigDecimal price;
        private Long locationId;
        private LocalDate activePublicationDate;
        @Enumerated(EnumType.STRING)
        private PublicationStatus publicationStatus;
        private LocalDate publicationDate;

    }

