package com.alextheracer1.portal2dpromax.api.entities.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {
  @Id private String userId;
  private Credentials credentials;
}
