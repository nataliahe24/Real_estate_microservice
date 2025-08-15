package com.powerup.realestate.properties.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerVisitEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String buyerEmail;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_schedule_id", nullable = false)
    private VisitScheduleEntity visitSchedule;
} 