package com.powerup.realestate.properties.infrastructure.repositories.mysql;

import com.powerup.realestate.properties.domain.utils.PublicationStatus;
import com.powerup.realestate.properties.infrastructure.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    List<PropertyEntity> findAllByPublicationStatus(PublicationStatus status);
}
