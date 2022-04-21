package com.alextheracer1.portal2dpromax.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "scores")
public class Score {
    private String name;
    private int score;
}
