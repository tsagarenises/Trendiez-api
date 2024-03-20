package com.api.trendiez.services;

import com.api.trendiez.Repository.ChatRepository;
import com.api.trendiez.Repository.UserRepository;
import com.api.trendiez.models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.api.trendiez.models.Message;
import com.api.trendiez.models.User;

import java.util.List;
import java.util.stream.Collectors;

@EnableMongoRepositories
@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    public Optional<Chat> findChatByUsers(String uid, String userId) {
        return chatRepository.findChatByUsersContains(uid, userId);
    }

    public Chat createChat(String uid, String userId) {
//        List<User> users = Arrays.asList(uid, userId)
//                .stream()
//                .map(id -> userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found: " + id)))
//                .collect(Collectors.toList());
        List<String> users = new ArrayList<>();
        users.add(uid);
        users.add(userId);
        Chat chat = new Chat();
        chat.setUsers(users);
        return chatRepository.save(chat);
    }

    public Chat findById(String id) {
        return chatRepository.findById(id).orElse(null);
    }
    public List<Chat> findAllChats() {
        return chatRepository.findAll();
    }

}
