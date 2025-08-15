package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.commons.configurations.utils.Constants;
import com.powerup.realestate.properties.domain.exceptions.ScheduleNotFountExceptions;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.domain.ports.out.BuyerVisitPersistencePort;
import com.powerup.realestate.properties.infrastructure.entities.BuyerVisitEntity;
import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import com.powerup.realestate.properties.infrastructure.mappers.BuyerVisitEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.BuyerVisitRepository;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.VisitScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BuyerVisitPersistenceAdapter implements BuyerVisitPersistencePort {

    private final BuyerVisitRepository buyerVisitRepository;
    private final BuyerVisitEntityMapper buyerVisitEntityMapper;
    private final VisitScheduleRepository visitScheduleRepository;

    @Override
    @Transactional
    public void save(BuyerVisitModel buyerVisit) {

        Long scheduleId = buyerVisit.getVisitSchedule().getId();
        if (!visitScheduleRepository.existsById(scheduleId)) {
            throw new ScheduleNotFountExceptions(Constants.VISIT_SCHEDULE_NO_FOUND);
        }

        int currentCount = countByVisitScheduleId(scheduleId);
        if (currentCount >= 2) {
            throw new IllegalStateException(Constants.ALREADY_MAX_VISITOR);
        }

        BuyerVisitEntity entity = buyerVisitEntityMapper.toEntity(buyerVisit);


        VisitScheduleEntity visitSchedule = visitScheduleRepository
                .findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFountExceptions(Constants.VISIT_SCHEDULE_NO_FOUND));


        entity.setVisitSchedule(visitSchedule);


        BuyerVisitEntity savedEntity = buyerVisitRepository.save(entity);


        buyerVisit.setId(savedEntity.getId());

    }

    @Override
    public List<BuyerVisitModel> findByVisitScheduleId(String buyerEmail) {
        List<BuyerVisitEntity> entities = buyerVisitRepository.findByVisitScheduleId(buyerEmail);
        return buyerVisitEntityMapper.toModelList(entities);
    }

    @Override
    public Optional<BuyerVisitModel> findByBuyerEmailAndVisitScheduleId(String buyerEmail, Long visitScheduleId) {
        return buyerVisitRepository.findByBuyerEmailAndVisitScheduleId(buyerEmail, visitScheduleId)
                .map(buyerVisitEntityMapper::toModel);
    }

    @Override
    public int countByVisitScheduleId(Long visitScheduleId) {
        return buyerVisitRepository.countByVisitScheduleId(visitScheduleId);
    }

    @Override
    public Optional<BuyerVisitModel> findById(Long visitId) {
        return buyerVisitRepository.findById(visitId)
                .map(buyerVisitEntityMapper::toModel);
    }

    @Override
    @Transactional
    public void delete(Long visitId) {
        buyerVisitRepository.deleteVisitById(visitId);
    }
    
    @Override
    public List<BuyerVisitModel> findBySellerId(Long sellerId) {
        List<BuyerVisitEntity> entities = buyerVisitRepository.findBySellerId(sellerId);
        return buyerVisitEntityMapper.toModelList(entities);
    }
} 