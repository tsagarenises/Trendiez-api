package com.api.trendiez.models;

public class LoginResponse {
    public User user;
    public String Token;

    public LoginResponse(String idTokenIsRequired) {
    }

    public LoginResponse(User user, String token) {
        this.user = user;
        Token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", Token='" + Token + '\'' +
                '}';
    }
}
