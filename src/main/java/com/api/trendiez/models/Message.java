package com.api.trendiez.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

    @Document(collection = "messages")
    public class Message {

        @Id
        private String id;

        private boolean isRead;

        private boolean isFile;

        private String content;

        @DBRef
        private Chat chat;

        @DBRef
        private User sender;

        private Date createdAt;

        private Date updatedAt;

        // getters and setters


        public Message getId() {
            return null;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }

        public boolean isFile() {
            return isFile;
        }

        public void setFile(boolean file) {
            isFile = file;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Chat getChat() {
            return chat;
        }

        public void setChat(Chat chat) {
            this.chat = chat;
        }

        public User getSender() {
            return sender;
        }

        public void setSender(User sender) {
            this.sender = sender;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        // override toString method to return id and chat as strings
        @Override
        public String toString() {
            return "Message{" +
                    "id='" + id + '\'' +
                    ", isRead=" + isRead +
                    ", isFile=" + isFile +
                    ", content='" + content + '\'' +
                    ", chat='" + chat.getId() + '\'' +
                    ", sender=" + sender +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    '}';
        }

        public String getChatId() {
            return "success";
        }
    }
