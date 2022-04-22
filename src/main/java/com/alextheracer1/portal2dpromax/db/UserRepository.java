package com.alextheracer1.portal2dpromax.db;

import com.alextheracer1.portal2dpromax.entities.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  boolean existsByUserId(String userId);
}
