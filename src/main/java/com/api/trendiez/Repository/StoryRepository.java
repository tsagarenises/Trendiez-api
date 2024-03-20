package com.api.trendiez.Repository;

import com.api.trendiez.models.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StoryRepository extends MongoRepository<Story, String> {
   int countStoriesByHourAndPlaceAndDay(int hour, String place, int day);

    List<Story> findByPlace(String place);
}
