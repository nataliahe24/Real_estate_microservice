package com.powerup.realestate.location.infrastructure.entities;

import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String neighborhood ;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity cityName;

}
