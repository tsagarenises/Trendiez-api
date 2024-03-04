package com.api.trendiez.services;

import com.api.trendiez.Repository.MessageRepository;
import com.api.trendiez.models.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Optional<Message> createMessage(Message message, String uid) {
        return messageRepository.findById(uid);
    }
}
