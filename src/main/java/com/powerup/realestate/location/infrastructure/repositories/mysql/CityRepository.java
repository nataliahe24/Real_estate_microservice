package com.powerup.realestate.location.infrastructure.repositories.mysql;

import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
