package com.api.trendiez.Repository;

import com.api.trendiez.models.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriesRepository extends MongoRepository<Categories, String> {
}
