package com.api.trendiez.Repository;

import com.api.trendiez.models.Friend;
import com.api.trendiez.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendRepository extends MongoRepository<Friend, String> {
}
