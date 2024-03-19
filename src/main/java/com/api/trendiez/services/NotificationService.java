package com.api.trendiez.services;

import com.api.trendiez.Repository.PlaceRepository;
import com.api.trendiez.models.Notification;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private PlaceRepository placeRepository;
    private final MongoTemplate mongoTemplate;

    public NotificationService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Notification> findByRecipientAndTypeOrderByCreatedAtDesc(String uid, String friend) {
        Query query = new Query();
        query.addCriteria(Criteria.where("recipient").is(new ObjectId(uid)));
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        return mongoTemplate.find(query, Notification.class);
    }

    public long countByRecipientAndTypeAndIsRead(String uid, String friend, boolean isRead) {
        Query query = new Query();
        query.addCriteria(Criteria.where("recipient").is(new ObjectId(uid)).and("type").is(friend).and("isRead").is(isRead));
        return mongoTemplate.count(query, Notification.class);
    }

    public Optional<Object> findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Notification notification = mongoTemplate.findOne(query, Notification.class);
        return Optional.ofNullable(notification);
    }

    public void save(Notification notification) {
        mongoTemplate.save(notification);
    }
}
