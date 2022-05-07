package com.alextheracer1.portal2dpromax.api.entities.score;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ScoreSaveRequest {
  @Id private String userId;
  private int score;
}
