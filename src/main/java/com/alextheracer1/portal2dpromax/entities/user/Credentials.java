package com.alextheracer1.portal2dpromax.entities.user;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import lombok.Data;

@Data
public class Credentials {
  private String username;
  private String password;

  public void hashPassword() {
    password = Hashing.sha256()
        .hashString(password, StandardCharsets.UTF_8)
        .toString();
  }
}
