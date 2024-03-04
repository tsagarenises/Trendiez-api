package com.api.trendiez.services;

import com.api.trendiez.Repository.StoryRepository;
import com.api.trendiez.models.Story;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
 private final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }
    public Story save(Story story){
        return storyRepository.save(story);
    }

    public List<Integer> getStoriesByDayAndPlace(String day, String place) {
        List<Integer> data = new ArrayList<>();

        // Loop through the hours of the day
        for (int i = 9; i <= 20; i++) {
            // Fetch and aggregate the stories for the given day, place and hour
            // You would need to replace this with your actual database query
           // int storyCount = fetchAndAggregateStories(day, place, i);

          //  data.add(storyCount);
        }

        return data;
    }

    public Optional<Story> getStoryById(String s) {
        return storyRepository.findById(s);
    }

    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    public List<Story> getSavedStories(String uid) {
        return storyRepository.findAllById(Collections.singleton(uid));
    }

    public List<Story> getStoriesByCategory(Object category) {
        return new ArrayList<> ();
    }
}
