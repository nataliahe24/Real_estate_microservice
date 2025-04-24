package com.powerup.realestate.properties.infrastructure.repositories.mysql;

import com.powerup.realestate.properties.infrastructure.entities.BuyerVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerVisitRepository extends JpaRepository<BuyerVisitEntity, Long> {
    
    @Query("SELECT b FROM BuyerVisitEntity b WHERE b.visitSchedule.id = :scheduleId")
    List<BuyerVisitEntity> findByVisitScheduleId(@Param("scheduleId") Long scheduleId);
    
    @Query("SELECT b FROM BuyerVisitEntity b WHERE b.buyerEmail = :email AND b.visitSchedule.id = :scheduleId")
    Optional<BuyerVisitEntity> findByBuyerEmailAndVisitScheduleId(
            @Param("email") String email, 
            @Param("scheduleId") Long scheduleId);
    
    @Query("SELECT COUNT(b) FROM BuyerVisitEntity b WHERE b.visitSchedule.id = :scheduleId")
    int countByVisitScheduleId(@Param("scheduleId") Long scheduleId);
} 