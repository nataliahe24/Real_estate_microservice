package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.properties.domain.exceptions.PropertyNotFoundException;
import com.powerup.realestate.properties.domain.model.BuyerVisitModel;
import com.powerup.realestate.properties.domain.model.VisitScheduleModel;
import com.powerup.realestate.properties.domain.ports.out.BuyerVisitPersistencePort;
import com.powerup.realestate.properties.infrastructure.entities.BuyerVisitEntity;
import com.powerup.realestate.properties.infrastructure.entities.VisitScheduleEntity;
import com.powerup.realestate.properties.infrastructure.mappers.BuyerVisitEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.BuyerVisitRepository;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.VisitScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuyerVisitPersistenceAdapter implements BuyerVisitPersistencePort {
    
    private final BuyerVisitRepository buyerVisitRepository;
    private final BuyerVisitEntityMapper buyerVisitEntityMapper;
    private final VisitScheduleRepository visitScheduleRepository;
    
    @Autowired
    public BuyerVisitPersistenceAdapter(BuyerVisitRepository buyerVisitRepository,
                                        BuyerVisitEntityMapper buyerVisitEntityMapper,
                                        VisitScheduleRepository visitScheduleRepository) {
        this.buyerVisitRepository = buyerVisitRepository;
        this.buyerVisitEntityMapper = buyerVisitEntityMapper;
        this.visitScheduleRepository = visitScheduleRepository;
    }
    
    @Override
    @Transactional
    public void save(BuyerVisitModel buyerVisit) {
        try {
            // Verificar que el horario existe
            Long scheduleId = buyerVisit.getVisitScheduleId();
            if (!visitScheduleRepository.existsById(scheduleId)) {
                throw new PropertyNotFoundException("El horario de visita no existe");
            }
            
            // Verificar si ya se alcanzó el límite de agendamientos
            int currentCount = countByVisitScheduleId(scheduleId);
            if (currentCount >= 2) {
                throw new IllegalStateException("Este horario ya tiene el máximo de 2 compradores agendados");
            }
            
            // Crear la entidad BuyerVisit
            BuyerVisitEntity entity = buyerVisitEntityMapper.toEntity(buyerVisit);
            
            // Obtener la entidad VisitSchedule
            VisitScheduleEntity visitSchedule = visitScheduleRepository
                    .findById(scheduleId)
                    .orElseThrow(() -> new PropertyNotFoundException("El horario de visita no existe"));
            
            // Establecer la relación
            entity.setVisitSchedule(visitSchedule);
            
            // Guardar la entidad BuyerVisit
            BuyerVisitEntity savedEntity = buyerVisitRepository.save(entity);
            
            // Actualizar el ID en el modelo
            buyerVisit.setId(savedEntity.getId());
            
        } catch (Exception e) {
            throw e;
        }
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
        buyerVisitRepository.deleteById(visitId);
    }
} 