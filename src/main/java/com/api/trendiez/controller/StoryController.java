package com.api.trendiez.controller;


import com.api.trendiez.models.Categories;
import com.api.trendiez.models.Story;
import com.api.trendiez.services.CategoriesService;
import com.api.trendiez.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/story")
public class StoryController {
    private final StoryService storyService;
    private final CategoriesService categoriesService;
    @Autowired
    public StoryController(StoryService storyService, CategoriesService categoriesService) {
        this.storyService = storyService;
        this.categoriesService = categoriesService;
    }

    @PostMapping("/create")
    public ResponseEntity<Story> createStory(@RequestBody Story story) {
        try {
            Story createdStory = storyService.save(story);
            return new ResponseEntity<>(createdStory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






    @GetMapping("/getStories")
    public ResponseEntity<?> getStory(@RequestParam Optional<String> id, @RequestParam Optional<String> day, @RequestParam Optional<String> place) {
        try {
            if (day.isPresent() && place.isPresent()) {
                // You would need to implement the aggregation logic in your service
                List<Integer> data = storyService.getStoriesByDayAndPlace(day.get(), place.get());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else if (id.isPresent()) {
                Optional<Story> story = storyService.getStoryById(id.get());
                return new ResponseEntity<>(story, HttpStatus.OK);
            } else {
                List<Story> stories = storyService.getAllStories();
                return new ResponseEntity<>(stories, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{uid}")
    public ResponseEntity<List<Story>> getSavedStories(@PathVariable String uid) {
        try {
            List<Story> stories = storyService.getSavedStories(uid);
            return new ResponseEntity<>(stories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getStoryCategories() {
        try {
            List<Categories> storyCategories = categoriesService.getAllCategories().stream()
                    .map(category -> {
                        List<Story> stories = storyService.getStoriesByCategory(category);
                        return new Categories(category, stories);
                    })
                    .sorted((a, b) -> b.getStories().size() - a.getStories().size())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(storyCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
