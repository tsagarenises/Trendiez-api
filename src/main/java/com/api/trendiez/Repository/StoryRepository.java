package com.api.trendiez.Repository;

import com.api.trendiez.models.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
}
