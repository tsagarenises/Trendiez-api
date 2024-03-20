package com.api.trendiez.controller;
import com.api.trendiez.models.Chat;
import com.api.trendiez.models.Message;
import com.api.trendiez.services.ChatService;
import com.api.trendiez.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final ChatService chatService;

    public MessageController(MessageService messageService, ChatService chatService) {
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createMessage(@RequestBody Message message, @PathVariable String uid) {
        try {
            Optional<Message> createdMessage = messageService.createMessage(message, uid);
//            Chat chat = chatService.updateLatestMessage(message.getChatId(), createdMessage.get());

//            if (chat == null) {
//                throw new Exception();
//            }

//            chatService.sendNotification(chat, createdMessage, uid);
//            chatService.sendMessage(chat, createdMessage);

            return new ResponseEntity<>("Successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

