package com.api.trendiez.models;

public class LoginRequest {
    public String IdToken;
    public String UserId;

    public String getIdToken() {
        return IdToken;
    }

    public void setIdToken(String idToken) {
        IdToken = idToken;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "IdToken='" + IdToken + '\'' +
                ", UserId='" + UserId + '\'' +
                '}';
    }
}
