package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.out.VisitSchedulePersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import com.powerup.realestate.properties.infrastructure.mappers.VisitScheduleEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.VisitScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitSchedulePersistenceAdapter implements VisitSchedulePersistencePort {
    private final VisitScheduleRepository visitScheduleRepository;
    private final VisitScheduleEntityMapper visitScheduleEntityMapper;

    @Autowired
    public VisitSchedulePersistenceAdapter(VisitScheduleRepository visitScheduleRepository,
                                         VisitScheduleEntityMapper visitScheduleEntityMapper) {
        this.visitScheduleRepository = visitScheduleRepository;
        this.visitScheduleEntityMapper = visitScheduleEntityMapper;
    }

    @Override
    public void save(VisitScheduleModel visitScheduleModel) {
        visitScheduleRepository.save(visitScheduleEntityMapper.toEntity(visitScheduleModel));
    }

    @Override
    public Optional<VisitScheduleModel> findById(Long id) {
        return visitScheduleRepository.findById(id)
                .map(visitScheduleEntityMapper::toModel);
    }

    @Override
    public boolean existsById(Long id) {
        return visitScheduleRepository.existsById(id);
    }

    @Override
    public List<VisitScheduleModel> findByPropertyId(Long propertyId) {
        List<VisitScheduleEntity> entities = visitScheduleRepository.findByPropertyId(propertyId);
        return visitScheduleEntityMapper.toModelList(entities);
    }

    @Override
    public List<VisitScheduleModel> findBySellerId(Long sellerId) {
        List<VisitScheduleEntity> entities = visitScheduleRepository.findBySellerId(sellerId);
        return visitScheduleEntityMapper.toModelList(entities);
    }

    @Override
    public PageResult<VisitScheduleModel> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VisitScheduleEntity> entityPage = visitScheduleRepository.findAll(pageable);
        List<VisitScheduleModel> modelList = visitScheduleEntityMapper.toModelList(entityPage.getContent());
        
        return new PageResult<>(
                modelList,
                page,
                size,
                (int) entityPage.getTotalElements()
        );
    }

    @Override
    public PageResult<VisitScheduleModel> findByFilter(
            LocalDateTime startDate,
            LocalDateTime endDate,
            String location,
            Integer page,
            Integer size) {
        
        Pageable pageable = PageRequest.of(page, size);
        
        Page<VisitScheduleEntity> entityPage = visitScheduleRepository.findFilteredSchedules(
                startDate,
                endDate,
                location,
                LocalDateTime.now(),
                pageable
        );
        
        List<VisitScheduleModel> modelList = visitScheduleEntityMapper.toModelList(entityPage.getContent());
        
        return new PageResult<>(
                modelList,
                page,
                size,
                (int) entityPage.getTotalElements()
        );
    }

    @Override
    public void updateScheduledBuyersCount(Long scheduleId, int count) {
        visitScheduleRepository.findById(scheduleId).ifPresent(schedule -> {
            schedule.setScheduledBuyers(count);
            visitScheduleRepository.save(schedule);
        });
    }

    @Override
    public PageResult<VisitScheduleModel> getSchedules(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VisitScheduleEntity> entityPage = visitScheduleRepository.findAllVisitsSimple(pageable);
        List<VisitScheduleModel> modelList = visitScheduleEntityMapper.toModelList(entityPage.getContent());
        
        return new PageResult<>(
                modelList,
                page,
                size,
                (int) entityPage.getTotalElements()
        );
    }

    @Override
    public PageResult<VisitScheduleModel> getFilteredSchedules(
            LocalDateTime startDate,
            LocalDateTime endDate,
            String location,
            Integer page,
            Integer size) {
        
        Pageable pageable = PageRequest.of(page, size);
        
        Page<VisitScheduleEntity> entityPage = visitScheduleRepository.findFilteredSchedules(
                startDate,
                endDate,
                location,
                LocalDateTime.now(),
                pageable
        );
        
        List<VisitScheduleModel> modelList = visitScheduleEntityMapper.toModelList(entityPage.getContent());
        
        return new PageResult<>(
                modelList,
                page,
                size,
                (int) entityPage.getTotalElements()
        );
    }
} 