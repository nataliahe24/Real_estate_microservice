package com.powerup.realestate.properties.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import static com.powerup.realestate.properties.domain.utils.constants.BuyerVisitDomainConstants.*;

@Getter
@Builder
public class BuyerVisitModel {
    @Setter
    private Long id;
    private Long visitScheduleId;
    private String buyerEmail;
    
    // Constructor personalizado con validaciones
    public BuyerVisitModel(Long id, Long visitScheduleId, String buyerEmail) {
        this.id = id;
        this.visitScheduleId = Objects.requireNonNull(visitScheduleId, FIELD_VISIT_SCHEDULE_ID_NULL_MESSAGE);
        this.buyerEmail = Objects.requireNonNull(buyerEmail, FIELD_BUYER_EMAIL_NULL_MESSAGE);
    }
    
    public void setVisitScheduleId(Long visitScheduleId) {
        this.visitScheduleId = Objects.requireNonNull(visitScheduleId, FIELD_VISIT_SCHEDULE_ID_NULL_MESSAGE);
    }
    
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = Objects.requireNonNull(buyerEmail, FIELD_BUYER_EMAIL_NULL_MESSAGE);
    }
} 