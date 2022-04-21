package com.alextheracer1.portal2dpromax.db;

import com.alextheracer1.portal2dpromax.entities.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScoreRepository extends MongoRepository<Score, String> {
    Optional<Score> findByName(String name);
}
