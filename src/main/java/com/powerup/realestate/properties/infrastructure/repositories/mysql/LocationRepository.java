package com.powerup.realestate.properties.infrastructure.repositories.mysql;

import com.powerup.realestate.properties.infrastructure.entities.LocationEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    @NonNull
    Optional<LocationEntity> findById(@NonNull Long id);

    @Query("""
                    SELECT l FROM LocationEntity l
            JOIN l.cityName c
            JOIN c.departmentEntity d
            WHERE LOWER(l.neighborhood) LIKE LOWER(CONCAT('%', :searchText, '%'))
               OR LOWER(c.name) LIKE LOWER(CONCAT('%', :searchText, '%'))
               OR LOWER(d.name) LIKE LOWER(CONCAT('%', :searchText, '%'))
            ORDER BY c.name ASC, d.name ASC
            """)
    Page<LocationEntity> findByCityOrDepartment(@Param("searchText") String searchText, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END " +
            "FROM LocationEntity l WHERE l.cityName.id = :cityId AND LOWER(l.neighborhood) = LOWER(:neighborhood)")
    boolean existsByCityName_IdAndNeighborhoodIgnoreCase(@Param("cityId") Long cityId, @Param("neighborhood") String neighborhood);
}
