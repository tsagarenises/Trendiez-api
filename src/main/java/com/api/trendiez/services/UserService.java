package com.api.trendiez.services;

import com.api.trendiez.Repository.UserRepository;
import com.api.trendiez.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserService(UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

//    public List<User> findUsersByLikeUsername(String search, String userId) {
//        Criteria criteria = Criteria.where("username").regex(search, Criteria.IgnoreCase.INSENSITIVE);
//        Query userQuery = new Query(criteria);
//        userQuery.addCriteria(Criteria.where("_id").ne(userId).andOperator(Criteria.where("isDeleted").is(false))); // Exclude current user and filter deleted users
//
//        List<User> users = mongoTemplate.find(userQuery, User.class);
//    }

    // Implement other methods as needed
}