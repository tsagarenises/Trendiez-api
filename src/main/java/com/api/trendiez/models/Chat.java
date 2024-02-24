package com.api.trendiez.models;

// import springboot and mongodb dependencies
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

// define the chat schema class
@Document(collection = "chats")
public class Chat {

    @Id
    private String id;

    @DBRef
    private List<User> users;

    @DBRef
    private Message latestMessage;

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Message getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(Message latestMessage) {
        this.latestMessage = latestMessage;
    }
}
