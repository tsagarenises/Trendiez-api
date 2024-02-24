package com.api.trendiez.services;

import com.api.trendiez.models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.api.trendiez.models.Message;
import com.api.trendiez.models.User;

import java.util.List;

@EnableMongoRepositories

@Service
public class ChatService {
    private MongoTemplate mongoTemplate;
    @Autowired
    public ChatService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public Chat findById(String id) {
        return mongoTemplate.findById(id, Chat.class);
    }

    public Chat findByUsers(String uid, String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("users").all(uid, userId));
        return mongoTemplate.findOne(query, Chat.class);
    }

    public List<Chat> findByUser(String uid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("users").in(uid));
        query.addCriteria(Criteria.where("latestMessage").exists(true));
        return mongoTemplate.find(query, Chat.class);
    }

    public Chat createChat(String userId) {
        Chat chat = new Chat();
        chat.setUsers(List.of( new User(userId)));
        return mongoTemplate.save(chat);
    }

    public Chat updateLatestMessage(String chatId, Message message) {
        Chat chat = findById(chatId);
        chat.setLatestMessage(message);
        return mongoTemplate.save(chat);
    }

    public Chat populateChat(Chat chat) {
        List<User> users = new ArrayList<>();
        for (User user : chat.getUsers()) {
            User fetchedUser = mongoTemplate.findById(user.getId(), User.class);
            users.add(fetchedUser);
        }
        chat.setUsers(users);

        Message message = mongoTemplate.findById(chat.getLatestMessage().getId(), Message.class);
        chat.setLatestMessage(message);

        return chat;
    }


    public List<Chat> populateChats(List<Chat> chats) {
        chats.forEach(this::populateChat);
        return chats;
    }
}
