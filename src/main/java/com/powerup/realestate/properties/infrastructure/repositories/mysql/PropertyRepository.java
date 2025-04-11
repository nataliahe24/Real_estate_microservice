package com.powerup.realestate.properties.infrastructure.repositories.mysql;

import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    List<PropertyEntity> findAllByPublicationStatus(PublicationStatus status);

    @Query(value = "SELECT p.* " +
            "FROM property_entity p " +
            "JOIN category_entity c ON p.category_id = c.id " +
            "JOIN location_entity l ON p.location_id = l.id " +
            "JOIN city_entity ci ON l.city_id = ci.id " +
            "JOIN department_entity d ON ci.department_id = d.id " +
            "WHERE p.publication_status = 'PUBLISHED' " +
            "AND (:location IS NULL OR " +
            "     LOWER(l.neighborhood) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
            "     LOWER(ci.name) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
            "     LOWER(d.name) LIKE LOWER(CONCAT('%', :location, '%'))) " +
            "AND (:propertyCategory IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :propertyCategory, '%'))) " +
            "AND (:rooms IS NULL OR p.rooms = :rooms) " +
            "AND (:bathrooms IS NULL OR p.bathrooms = :bathrooms) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)",

            countQuery = "SELECT COUNT(*) FROM property_entity p " +
                    "JOIN category_entity c ON p.category_id = c.id " +
                    "JOIN location_entity l ON p.location_id = l.id " +
                    "JOIN city_entity ci ON l.city_id = ci.id " +
                    "JOIN department_entity d ON ci.department_id = d.id " +
                    "WHERE p.publication_status = 'PUBLISHED' " +
                    "AND (:location IS NULL OR " +
                    "     LOWER(l.neighborhood) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
                    "     LOWER(ci.name) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
                    "     LOWER(d.name) LIKE LOWER(CONCAT('%', :location, '%'))) " +
                    "AND (:propertyCategory IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :propertyCategory, '%'))) " +
                    "AND (:rooms IS NULL OR p.rooms = :rooms) " +
                    "AND (:bathrooms IS NULL OR p.bathrooms = :bathrooms) " +
                    "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
                    "AND (:maxPrice IS NULL OR p.price <= :maxPrice)",

            nativeQuery = true)
    Page<PropertyEntity> findPropertiesWithFiltersAndOrder(
            @Param("location") String location,
            @Param("propertyCategory") String category,
            @Param("rooms") Integer rooms,
            @Param("bathrooms") Integer bathrooms,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("sortBy") String sortBy,
            @Param("orderAsc") boolean orderAsc,
            Pageable pageable
    );

}