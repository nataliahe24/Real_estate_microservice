package com.powerup.realestate.location.infrastructure.repositories.mysql;

import com.powerup.realestate.location.infrastructure.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT c FROM CityEntity c WHERE LOWER(TRIM(REPLACE(c.name, ' ', ''))) = :cleanedName")
    CityEntity findByNameIgnoreCaseAndTrim(@Param("cleanedName") String cleanedName);

}

