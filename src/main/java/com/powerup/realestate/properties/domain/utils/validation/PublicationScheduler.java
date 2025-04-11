package com.powerup.realestate.properties.domain.utils.validation;

import com.powerup.realestate.properties.domain.model.PropertyModel;
import com.powerup.realestate.properties.domain.ports.out.PropertyPersistencePort;
import com.powerup.realestate.properties.domain.utils.PublicationStatus;
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
        List<PropertyModel> pausedProperties = propertyPersistencePort
                .findByPublicationStatus(PublicationStatus.PUBLISHING_PAUSED);

        for (PropertyModel property : pausedProperties) {
            if (property.getActivePublicationDate() != null &&
                    !property.getActivePublicationDate().isAfter(LocalDate.now())) {

                property.setPublicationStatus(PublicationStatus.PUBLISHED);
                propertyPersistencePort.update(property);
            }
        }
    }
}
