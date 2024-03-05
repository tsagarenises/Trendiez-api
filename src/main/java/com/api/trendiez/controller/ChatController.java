package com.api.trendiez.controller;
import com.api.trendiez.Repository.ChatRepository;
import com.api.trendiez.Repository.MessageRepository;
import com.api.trendiez.Repository.UserRepository;
import com.api.trendiez.config.JwtUtil;
import com.api.trendiez.models.Chat;
import com.api.trendiez.models.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/{userId}")
    public ResponseEntity<?> getChatByUserId(@PathVariable String userId, @RequestHeader("Authorization") String token) {
        String uid = jwtUtil.extractUserId(token.substring(7)); // Remove "Bearer " from token
        List<Chat> chats = chatRepository.findByUsersContaining(uid);

        if (!chats.isEmpty()) {
            Chat chat = chats.get(0);
            // Manually populate users and latestMessage
            List<String> userIds = chat.getUsers().stream().map(User::getId).collect(Collectors.toList());
            chat.setUsers(userRepository.findAllById(userIds));
            //chat.setLatestMessage(messageRepository.findById(chat.getLatestMessage().getId()).orElse(null));
            return ResponseEntity.ok(chat);
        } else {
            Chat newChat = new Chat();
            newChat.setUsers(Arrays.asList(userRepository.findById(uid).orElse(null), userRepository.findById(userId).orElse(null)));
            chatRepository.save(newChat);
            List<String> newUserIds = newChat.getUsers().stream().map(User::getId).collect(Collectors.toList());
            newChat.setUsers(userRepository.findAllById(newUserIds));
            return ResponseEntity.ok(newChat);
        }
    }





//    @GetMapping("/")
//    public ResponseEntity<?> getChats(@RequestParam(required = false) String id) {
//        if (id != null) {
//            Optional<Chat> chat = chatRepository.findById(id);
//            return chat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        } else {
//            List<Chat> chats = chatRepository.findByUsersContaining(/* Insert authenticated user's ID here */);
//            return ResponseEntity.ok(chats);
//        }
//    }
}
