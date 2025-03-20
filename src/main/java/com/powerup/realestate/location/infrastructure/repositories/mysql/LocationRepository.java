package com.powerup.realestate.location.infrastructure.repositories.mysql;

import com.powerup.realestate.location.infrastructure.entities.LocationEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    @Query("SELECT l FROM LocationEntity l WHERE " +
            "LOWER(l.city) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
            "OR LOWER(l.department) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    Page<LocationEntity> findByCityOrDepartment(@Param("searchText") String searchText, Pageable pageable);
}
