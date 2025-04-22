package com.powerup.realestate.properties.infrastructure.repositories.mysql;

import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitScheduleRepository extends JpaRepository<VisitScheduleEntity, Long> {
    List<VisitScheduleEntity> findByPropertyId(Long propertyId);
    List<VisitScheduleEntity> findBySellerId(Long sellerId);
    Page<VisitScheduleEntity> findAll(Pageable pageable);
} 