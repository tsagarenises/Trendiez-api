package com.api.trendiez.controller;

import com.api.trendiez.models.Place;
import com.api.trendiez.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;
    @GetMapping
    public ResponseEntity<List<Place>> getPlaces(@RequestParam(required = false) String search) throws Exception {
        try {
            List<Place> places = placeService.findPlaces(search);
            return ResponseEntity.ok(places);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
