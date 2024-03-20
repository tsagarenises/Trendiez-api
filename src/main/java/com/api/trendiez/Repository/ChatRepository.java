package com.api.trendiez.Repository;
import com.api.trendiez.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    Optional<Chat> findChatByUsersContains(String uid, String userId);
}
