package com.api.trendiez.services;

import com.api.trendiez.Repository.PlaceRepository;
import com.api.trendiez.models.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    private final MongoTemplate mongoTemplate;
    @Autowired
    public PlaceService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public List<Place> findPlaces(String search) throws Exception {
        if (search != null && !search.isEmpty()) {
            Criteria criteria = Criteria.where("name").regex(".*"+search+"*");
            Query query = new Query(criteria);
            List<Place> documents = mongoTemplate.find(query, Place.class);
            return documents;
        } else {
            return placeRepository.findAll();
        }
    }

    public Place createPlaceFromGoogleMaps(String placeId, String apiKey) throws Exception {
        // Extract relevant information from place details
        String name = "...";
        double rating = 0;
        String address = "...";
        String type = "...";
        double lat = 0;
        double lng = 0;

        // Create a new Place object
        //Place place = new Place(name, rating, address, placeId, type, lat, lng);
        Place place = new Place();

        place.setName(name);
        place.setRating((int) rating);
        place.setAddress(address);
        place.setPlaceId(placeId);
        place.setType(type);
        place.setLocation(new GeoJsonPoint(lat, lng));

        // Save the place in the database
        placeRepository.save(place);

        return place;
    }
}
