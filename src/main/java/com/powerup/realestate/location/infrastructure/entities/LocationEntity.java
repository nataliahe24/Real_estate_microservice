package com.powerup.realestate.location.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "neighborhood")
    private String neighborhood ;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, referencedColumnName = "id")
    private CityEntity cityName;

}
