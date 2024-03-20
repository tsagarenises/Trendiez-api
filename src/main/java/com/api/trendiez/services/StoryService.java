package com.api.trendiez.services;

import com.api.trendiez.Repository.StoryRepository;
import com.api.trendiez.models.Story;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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


    public Optional<Story> getStoryById(String id) {
        return storyRepository.findById(id);
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
