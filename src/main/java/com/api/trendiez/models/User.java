package com.api.trendiez.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @TextIndexed
    private String name;

    private String phone;

    private String email;

    private String about;

    private String gender;

    private String address;

    private String picture;

    private String username;

    private boolean isDeleted;

    private String fcmToken;

    private List<String> interests;

    private String password;

   // @Field("authentication.type")
    private String authenticationType = "email";

    @Field("authentication.enum")
    private List<String> authenticationEnum;

    private GeoJsonPoint location = new GeoJsonPoint(0.4444,3.444);

    private Date createdAt;

    private Date updatedAt;

    public User(String uid) {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.about = "";
        this.createdAt = new Date();
        this.address = "";
        this.gender = "";
        this.picture = "";
        this.updatedAt = new Date();
        this.interests = new ArrayList<>();
        this.phone = "";
        this.username = email;
        this.name = email;
        this.fcmToken = "";
        this.authenticationEnum = new ArrayList<>();


    }

    public User() {

    }
    // getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public List<String> getAuthenticationEnum() {
        return authenticationEnum;
    }

    public void setAuthenticationEnum(List<String> authenticationEnum) {
        this.authenticationEnum = authenticationEnum;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
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

    // override toString method to return id and location as strings
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", about='" + about + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", username='" + username + '\'' +
                ", isDeleted=" + isDeleted +
                ", fcmToken='" + fcmToken + '\'' +
                ", interests=" + interests +
                ", password='" + password + '\'' +
                ", authenticationType='" + authenticationType + '\'' +
                ", authenticationEnum=" + authenticationEnum +
                ", location='" + location.toString() + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
