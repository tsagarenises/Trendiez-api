package com.api.trendiez.services;

import com.api.trendiez.Repository.PlaceRepository;
import com.api.trendiez.models.Friend;
import com.api.trendiez.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    private PlaceRepository placeRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public FriendService(PlaceRepository placeRepository, MongoTemplate mongoTemplate) {
        this.placeRepository = placeRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Friend findFriendByUsers(String userId, String id) {
    }

    public Friend createFriend(String userId, User user) {
    }
}
