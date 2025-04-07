package com.powerup.realestate.properties.domain.utils;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PublicationScheduler {

    private final PropertyPersistencePort propertyPersistencePort;

    @Scheduled(fixedRate = 86400000)
    public void activatePendingPublications() {
        List<PropertyModel> pausedProperties = propertyPersistencePort.findByPublicationStatus(PublicationStatus.PUBLISHING_PAUSED);

        for (PropertyModel property : pausedProperties) {
            if (property.getActivePublicationDate() != null &&
                    !property.getActivePublicationDate().isAfter(LocalDate.now().plusDays(1))) {

                property.setPublicationStatus(PublicationStatus.PUBLISHED);
                propertyPersistencePort.update(property);
            }
        }
    }
}
