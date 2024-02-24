package com.api.trendiez.Repository;
import com.api.trendiez.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    // No need for findById, it's already provided by MongoRepository

    // No need for save, it's already provided by MongoRepository

    // Add other CRUD methods as needed

}
