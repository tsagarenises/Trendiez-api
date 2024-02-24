package com.api.trendiez.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reset")
public class Reset {

    @Id
    private String id;

    private boolean valid;

    @TextIndexed
    private String email;

    public Reset() {
        this.valid = true;
    }

    public Reset(String email) {
        this.valid = true;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reset{" +
                "id='" + id + '\'' +
                ", valid=" + valid +
                ", email='" + email + '\'' +
                '}';
    }
}
