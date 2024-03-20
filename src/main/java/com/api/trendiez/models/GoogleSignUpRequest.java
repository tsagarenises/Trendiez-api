package com.api.trendiez.models;

public class GoogleSignUpRequest {
    private String idToken;
    private String userId;

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GoogleSignUpRequest{" +
                "idToken='" + idToken + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
