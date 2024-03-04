package com.api.trendiez.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

// import springboot and mongodb dependencies
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// define the categories class
@Document(collection = "categories")
public class Categories {

    @Id
    private String id;

    // initialize the categories with the given values
    private String dinningLabel = "Dinning";
    private List<String> dinningItems = Arrays.asList("food", "cafe", "bakery", "restaurant", "meal_delivery", "meal_takeaway");

    private String attractionsLabel = "Attractions";
    private List<String> attractionsItems = Arrays.asList("zoo", "museum", "landmark", "aquarium", "art_gallery", "travel_agency", "amusement_park", "tourist_attraction");

    private String outdoorLabel = "Outdoor";
    private List<String> outdoorItems = Arrays.asList("spa", "gym", "park", "mosque", "church", "airport", "stadium", "campground", "beauty_salon", "movie_theater", "place_of_worship");

    private String shoppingLabel = "Shopping";
    private List<String> shoppingItems = Arrays.asList("supermarket", "shopping_mall", "clothing_store", "convenience_store");

    private String hotelsLabel = "Hotels";
    private List<String> hotelsItems = Arrays.asList("lodging");

    private String barLabel = "Bar";
    private List<String> barItems = Arrays.asList("bar", "liquor_store", "night_club", "casino");

    public Categories(Object category, List<Story> stories) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDinningLabel() {
        return dinningLabel;
    }

    public void setDinningLabel(String dinningLabel) {
        this.dinningLabel = dinningLabel;
    }

    public List<String> getDinningItems() {
        return dinningItems;
    }

    public void setDinningItems(List<String> dinningItems) {
        this.dinningItems = dinningItems;
    }

    public String getAttractionsLabel() {
        return attractionsLabel;
    }

    public void setAttractionsLabel(String attractionsLabel) {
        this.attractionsLabel = attractionsLabel;
    }

    public List<String> getAttractionsItems() {
        return attractionsItems;
    }

    public void setAttractionsItems(List<String> attractionsItems) {
        this.attractionsItems = attractionsItems;
    }

    public String getOutdoorLabel() {
        return outdoorLabel;
    }

    public void setOutdoorLabel(String outdoorLabel) {
        this.outdoorLabel = outdoorLabel;
    }

    public List<String> getOutdoorItems() {
        return outdoorItems;
    }

    public void setOutdoorItems(List<String> outdoorItems) {
        this.outdoorItems = outdoorItems;
    }

    public String getShoppingLabel() {
        return shoppingLabel;
    }

    public void setShoppingLabel(String shoppingLabel) {
        this.shoppingLabel = shoppingLabel;
    }

    public List<String> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<String> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public String getHotelsLabel() {
        return hotelsLabel;
    }

    public void setHotelsLabel(String hotelsLabel) {
        this.hotelsLabel = hotelsLabel;
    }

    public List<String> getHotelsItems() {
        return hotelsItems;
    }

    public void setHotelsItems(List<String> hotelsItems) {
        this.hotelsItems = hotelsItems;
    }

    public String getBarLabel() {
        return barLabel;
    }

    public void setBarLabel(String barLabel) {
        this.barLabel = barLabel;
    }

    public List<String> getBarItems() {
        return barItems;
    }

    public void setBarItems(List<String> barItems) {
        this.barItems = barItems;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id='" + id + '\'' +
                ", dinningLabel='" + dinningLabel + '\'' +
                ", dinningItems=" + dinningItems +
                ", attractionsLabel='" + attractionsLabel + '\'' +
                ", attractionsItems=" + attractionsItems +
                ", outdoorLabel='" + outdoorLabel + '\'' +
                ", outdoorItems=" + outdoorItems +
                ", shoppingLabel='" + shoppingLabel + '\'' +
                ", shoppingItems=" + shoppingItems +
                ", hotelsLabel='" + hotelsLabel + '\'' +
                ", hotelsItems=" + hotelsItems +
                ", barLabel='" + barLabel + '\'' +
                ", barItems=" + barItems +
                '}';
    }

    public Collection<Object> getStories() {
        return new ArrayList<>();
    }
}
