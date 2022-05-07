package com.alextheracer1.portal2dpromax.api.db;

import com.alextheracer1.portal2dpromax.api.entities.user.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  boolean existsByUserId(String userId);

  Optional<User> findUsernameByUserId(String userId);
}
