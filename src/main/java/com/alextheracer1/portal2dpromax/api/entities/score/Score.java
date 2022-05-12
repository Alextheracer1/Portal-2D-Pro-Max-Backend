package com.alextheracer1.portal2dpromax.api.entities.score;

import com.alextheracer1.portal2dpromax.service.RestService;
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

  @Data
  public static class ScorePresentation {

    String username;
    int score;

    private ScorePresentation(String username, int score) {
      this.username = username;
      this.score = score;
    }
  }

  public ScorePresentation toPresentation(RestService restService) {
    String username = restService.getUsernameFromID(this.userId);
    return new ScorePresentation(username, score);
  }
}
