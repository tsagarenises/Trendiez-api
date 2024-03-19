package com.api.trendiez.Repository;

import com.api.trendiez.models.Notification;
import com.api.trendiez.models.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
