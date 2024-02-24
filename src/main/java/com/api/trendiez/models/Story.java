package com.api.trendiez.models;

// import springboot and mongodb dependencies
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

// define the story schema class
@Document(collection = "stories")
public class Story {

    @Id
    private String id;

    private String file;
    private int rating;
    private String category;
    private boolean isSaved;
    private boolean isDeleted;
    private String caption;
    private List<String> views;

    @DBRef
    private User owner;

    @DBRef
    private Place place;


    private Categories categories; // embed the categories class as a field

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<String> getViews() {
        return views;
    }

    public void setViews(List<String> views) {
        this.views = views;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id='" + id + '\'' +
                ", file='" + file + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", isSaved=" + isSaved +
                ", isDeleted=" + isDeleted +
                ", caption='" + caption + '\'' +
                ", views=" + views +
                ", owner=" + owner +
                ", place=" + place +
                ", categories=" + categories +
                '}';
    }
}