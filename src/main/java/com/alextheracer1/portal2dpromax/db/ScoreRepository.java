package com.alextheracer1.portal2dpromax.db;

import com.alextheracer1.portal2dpromax.entities.Score;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepository extends MongoRepository<Score, String> {
  Optional<Score> findByName(String name);
}
