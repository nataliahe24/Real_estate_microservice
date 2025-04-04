package com.powerup.realestate.location.infrastructure.repositories.mysql;

import com.powerup.realestate.location.infrastructure.entities.LocationEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    @Query("SELECT l FROM LocationEntity l\n" +
            "JOIN l.cityId c\n" +
            "JOIN c.departmentEntity d\n" +
            "WHERE LOWER(l.neighborhood) LIKE LOWER(CONCAT('%', :searchText, '%'))\n" +
            "   OR LOWER(c.name) LIKE LOWER(CONCAT('%', :searchText, '%'))\n" +
            "   OR LOWER(d.name) LIKE LOWER(CONCAT('%', :searchText, '%'))\n" +
            "ORDER BY c.name ASC, d.name ASC")  // Aquí busca en el departamento
    Page<LocationEntity> findByCityOrDepartment(@Param("searchText") String searchText, Pageable pageable);
}
