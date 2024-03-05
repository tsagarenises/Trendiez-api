package com.api.trendiez.Repository;

import com.api.trendiez.models.Place;
import com.api.trendiez.models.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<Place, String> {
}
