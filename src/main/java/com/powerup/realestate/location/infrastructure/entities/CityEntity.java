package com.powerup.realestate.location.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(targetEntity = DepartmentEntity.class)
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;
    @OneToMany(mappedBy = "cityName", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LocationEntity> locations;

}
