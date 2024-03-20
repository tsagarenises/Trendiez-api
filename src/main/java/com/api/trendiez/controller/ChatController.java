package com.api.trendiez.controller;
import com.api.trendiez.Repository.ChatRepository;
import com.api.trendiez.models.Chat;
import com.api.trendiez.models.User;
import com.api.trendiez.services.ChatService;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/{userId}")
    public ResponseEntity<Chat> getChat(@PathVariable String userId, @RequestHeader("uid") String uid) {
        try {
            Optional<Chat> chat = chatService.findChatByUsers(uid, userId);

            if (chat.isPresent()) {
                return ResponseEntity.ok(chat.get());
            } else {
                Chat newChat = chatService.createChat(uid, userId);
                return ResponseEntity.ok(newChat);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing request", e);
        }
    }
    @GetMapping("/chats")
    public ResponseEntity<?> getChat(@RequestParam(required = false) String id) {
        try {
            if (id != null) {
                Chat chat = chatService.findById(id);
                return new ResponseEntity<>(chat, HttpStatus.OK);
            } else {
                List<Chat> chats = chatService.findAllChats();
                return new ResponseEntity<>(chats, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

