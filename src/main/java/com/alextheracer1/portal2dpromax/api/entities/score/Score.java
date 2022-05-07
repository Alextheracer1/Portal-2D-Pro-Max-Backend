package com.alextheracer1.portal2dpromax.api.entities.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "scores")
public class Score {
  @Id private String userId;
  private int score;
}
