package com.powerup.realestate.location.infrastructure.repositories.mysql;

import com.powerup.realestate.location.infrastructure.entities.LocationEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    boolean existsByDepartment(String department);
}
