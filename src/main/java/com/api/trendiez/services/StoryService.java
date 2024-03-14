package com.api.trendiez.services;

import com.api.trendiez.Repository.StoryRepository;
import com.api.trendiez.models.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
 private final StoryRepository storyRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public StoryService(StoryRepository storyRepository, MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
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
    public void addViewToStory(String storyId, String userId) throws Exception {
        Query query = new Query(Criteria.where("_id").is(storyId));
        Update update = new Update().addToSet("views", userId);
        mongoTemplate.updateFirst(query, update, Story.class);
    }
}
