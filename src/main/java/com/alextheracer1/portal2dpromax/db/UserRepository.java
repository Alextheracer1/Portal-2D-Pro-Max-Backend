package com.alextheracer1.portal2dpromax.db;

import com.alextheracer1.portal2dpromax.entities.score.Score;
import com.alextheracer1.portal2dpromax.entities.user.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  boolean existsByUserId(String userId);

  Optional<User> findUsernameByUserId(String userId);
}
