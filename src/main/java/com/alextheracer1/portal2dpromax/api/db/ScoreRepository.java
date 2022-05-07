package com.alextheracer1.portal2dpromax.api.db;

import com.alextheracer1.portal2dpromax.api.entities.score.Score;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepository extends MongoRepository<Score, String> {
  Optional<Score> findByUserId(String userId);

  boolean existsByUserId(String userId);
}
