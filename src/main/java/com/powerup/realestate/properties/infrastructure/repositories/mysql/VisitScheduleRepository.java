package com.powerup.realestate.properties.infrastructure.repositories.mysql;

import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitScheduleRepository extends JpaRepository<VisitScheduleEntity, Long> {
    List<VisitScheduleEntity> findByPropertyId(Long propertyId);
    List<VisitScheduleEntity> findBySellerId(Long sellerId);
    @NonNull
    Page<VisitScheduleEntity> findAll(@NonNull Pageable pageable);

    @Query(value = "SELECT v.* FROM visit_schedule_entity v " +
            "JOIN property_entity p ON v.property_id = p.id " +
            "JOIN location_entity l ON p.location_id = l.id " +
            "JOIN city_entity ci ON l.city_id = ci.id " +
            "JOIN department_entity d ON ci.department_id = d.id " +
            "WHERE v.start_date >= CURRENT_TIMESTAMP " +
            "AND (:startDate IS NULL OR v.start_date >= :startDate) " +
            "AND (:endDate IS NULL OR v.end_date <= :endDate) " +
            "AND (:location IS NULL OR ( " +
            "     LOWER(l.neighborhood) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
            "     LOWER(ci.name) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
            "     LOWER(d.name) LIKE LOWER(CONCAT('%', :location, '%')))) " +
            "AND v.scheduled_buyers < 2 " +
            "ORDER BY v.start_date DESC",

            countQuery = "SELECT COUNT(*) FROM visit_schedule_entity v " +
                    "JOIN property_entity p ON v.property_id = p.id " +
                    "JOIN location_entity l ON p.location_id = l.id " +
                    "JOIN city_entity ci ON l.city_id = ci.id " +
                    "JOIN department_entity d ON ci.department_id = d.id " +
                    "WHERE v.start_date >= CURRENT_TIMESTAMP " +
                    "AND (:startDate IS NULL OR v.start_date >= :startDate) " +
                    "AND (:endDate IS NULL OR v.end_date <= :endDate) " +
                    "AND (:location IS NULL OR ( " +
                    "     LOWER(l.neighborhood) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
                    "     LOWER(ci.name) LIKE LOWER(CONCAT('%', :location, '%')) OR " +
                    "     LOWER(d.name) LIKE LOWER(CONCAT('%', :location, '%')))) " +
                    "AND v.scheduled_buyers < 2",
            nativeQuery = true)
    Page<VisitScheduleEntity> findFilteredSchedules(
           @Param("startDate") LocalDateTime startDate,
           @Param("endDate") LocalDateTime endDate,
           @Param("location") String location,
           @Param("currentDateTime") LocalDateTime currentDateTime,
           Pageable pageable);

    @Query(value = "SELECT v.* FROM visit_schedule_entity v ",
           countQuery = "SELECT COUNT(*) FROM visit_schedule_entity v",
           nativeQuery = true)
    Page<VisitScheduleEntity> findAllVisitsSimple(Pageable pageable);

    @Query("SELECT COUNT(v) > 0 FROM VisitScheduleEntity v " +
            "WHERE v.property.id = :propertyId " +
            "AND (:startDate < v.endDate AND :endDate > v.startDate)")
    boolean existsByPropertyAndScheduleOverlap(
            @Param("propertyId") Long propertyId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

} 