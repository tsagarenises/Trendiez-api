package com.api.trendiez.services;

import com.api.trendiez.Repository.FriendRepository;
import com.api.trendiez.models.Friend;
import com.api.trendiez.models.Place;
import com.api.trendiez.models.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;
    private final MongoTemplate mongoTemplate;

    public FriendService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Friend> searchFriends(String search, User user) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("requester").is(new ObjectId(search)),
                Criteria.where("recipient").is(new ObjectId(search))
        ).and("status").is("friends");
        Query query = new Query(criteria);

        return mongoTemplate.find(query, Friend.class);
    }

    public List<Friend> getFriend(String userId, User user) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("requester").is(new ObjectId(userId)),
                Criteria.where("recipient").is(new ObjectId(userId))
        ).and("status").is("friends");
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Friend.class);
    }

    public long getFriendCount(String userId) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("requester").is(new ObjectId(userId)),
                Criteria.where("recipient").is(new ObjectId(userId))
        ).and("status").is("friends");
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.count(query, Friend.class);
    }

    public List<Friend> getSubscribers(String userId) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("recipient").is(new ObjectId(userId))
        ).and("status").is("friends");
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Friend.class);
    }
}
