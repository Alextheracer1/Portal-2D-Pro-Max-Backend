package com.alextheracer1.portal2dpromax.api;

import com.alextheracer1.portal2dpromax.api.db.ScoreRepository;
import com.alextheracer1.portal2dpromax.api.db.UserRepository;
import com.alextheracer1.portal2dpromax.api.entities.score.Score;
import com.alextheracer1.portal2dpromax.api.entities.user.Credentials;
import com.alextheracer1.portal2dpromax.api.entities.user.User;
import com.google.common.hash.Hashing;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);
  private final ScoreRepository scoreRepo;
  private final UserRepository userRepo;

  public Controller(ScoreRepository scoreRepo, UserRepository userRepo) {
    this.scoreRepo = scoreRepo;
    this.userRepo = userRepo;
  }

  @ApiResponse(responseCode = "200", description = "Returns the UserId for a given Username")
  @ApiResponse(responseCode = "400", description = "No user found for given Username")
  @GetMapping("getUserId/{username}")
  public ResponseEntity<String> getUserId(@PathVariable String username) {
    var all = userRepo.findAll();

    List<String> userId =
        all.stream()
            .filter(user -> user.getCredentials().getUsername().equals(username))
            .map(User::getUserId)
            .toList();

    return ResponseEntity.ok(userId.get(0));
  }

  @ApiResponse(responseCode = "200", description = "Return a username for a given UUID")
  @ApiResponse(responseCode = "400", description = "No user found for given UUID")
  @GetMapping("/getUsername/{userId}")
  public ResponseEntity<String> getUsername(@PathVariable String userId) {
    if (!userRepo.existsByUserId(userId)) {
      return ResponseEntity.badRequest().body("No user found for given UUID");
    }

    var all = userRepo.findUsernameByUserId(userId);

    List<String> username =
        all.stream()
            .map(User::getCredentials) // gets all credentials from user
            .map(Credentials::getUsername) // gets username from credentials
            .toList();

    return ResponseEntity.ok(username.get(0));
  }

  @ApiResponse(responseCode = "200", description = "Login successful")
  @ApiResponse(responseCode = "400", description = "Invalid credentials")
  @PostMapping("/checkLogin/{username}/{password}")
  public ResponseEntity<String> checkLogin(
      @PathVariable String username, @PathVariable String password) {

    String hashedPassword =
        Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

    if (userRepo.findAll().stream()
        .map(User::getCredentials)
        .anyMatch(credentials -> credentials.getUsername().equals(username))) {
      if (userRepo.findAll().stream()
          .map(User::getCredentials)
          .anyMatch(credentials -> credentials.getPassword().equals(hashedPassword))) {
        return ResponseEntity.status(200).body("Login successful");
      }
    }
    return ResponseEntity.status(400).body("Invalid credentials");
  }

  @ApiResponse(responseCode = "200", description = "Returns all the scores with UUIDs")
  @GetMapping("/getScores")
  public ResponseEntity<List<Score>> getScores() {
    return ResponseEntity.ok(scoreRepo.findAll());
  }

  @ApiResponse(responseCode = "200", description = "Returns the top 20 scores")
  @GetMapping("/getTopScores")
  public ResponseEntity<List<Score>> getTopScores() {
    return ResponseEntity.ok(scoreRepo.findTop20ItemsByOrderByScoreDesc());
  }

  @ApiResponse(responseCode = "200", description = "Returns a score for a UUID")
  @ApiResponse(responseCode = "400", description = "No Score for given UUID was found")
  @GetMapping("/getScore/{userId}")
  public ResponseEntity<String> getScore(@PathVariable String userId) {
    if (!scoreRepo.existsByUserId(userId)) {
      return ResponseEntity.badRequest().body("No score for given userID found");
    }
    var all = scoreRepo.findByUserId(userId);
    List<Integer> score = all.stream().map(Score::getScore).toList();
    return ResponseEntity.ok(score.toString());
  }

  @ApiResponse(responseCode = "200", description = "Saves a score to the database")
  @ApiResponse(responseCode = "400", description = "UserId is not valid")
  @ApiResponse(responseCode = "401", description = "New Score is older than the old Score")
  @PostMapping("/saveScore/{userId}/{score}")
  public ResponseEntity<String> saveScore(@PathVariable String userId, @PathVariable int score) {
    System.out.println("Score creation started...");

    if (!userRepo.existsByUserId(userId)) {
      return ResponseEntity.badRequest().body("User does not exist");
    }
    var all = scoreRepo.findByUserId(userId);
    List<Integer> oldScore = all.stream().map(Score::getScore).toList();

    if (!oldScore.isEmpty()) {
      if (oldScore.get(0) > score) {
        log.info(oldScore.get(0).toString());
        return ResponseEntity.status(401).body("Score is lower than previous score");
      }
    }

    Score newScore = new Score(userId, score);
    scoreRepo.save(newScore);
    System.out.println("Score creation complete...");
    return ResponseEntity.ok("Score saved");
  }

  @ApiResponse(responseCode = "200", description = "Returns all the users found in the database")
  @GetMapping("/getUsers")
  public ResponseEntity<String> getUsers() {
    return ResponseEntity.ok(userRepo.findAll().toString());
  }

  @ApiResponse(
      responseCode = "200",
      description =
          "Saves a new user to the database and creates a new UUID and SHA-256 hash for the"
              + " password")
  @ApiResponse(responseCode = "400", description = "User already exists")
  @PostMapping("/saveUser")
  public ResponseEntity<String> saveUser(@ModelAttribute Credentials credentials) {
    log.info("User creation started...");

    if (userRepo.findAll().stream()
        .map(User::getCredentials)
        .anyMatch(cred -> cred.getUsername().equals(credentials.getUsername()))) {
      return ResponseEntity.badRequest().body("User already exists");
    }

    // uses google's hashing library to hash password into an SHA-256 hash
    credentials.hashPassword();

    String userId = UUID.randomUUID().toString();
    User user = new User(userId, credentials);

    userRepo.save(user);

    log.info("User creation complete...");

    return ResponseEntity.ok("User created");
  }
}
