package com.alextheracer1.portal2dpromax;

import com.alextheracer1.portal2dpromax.db.ScoreRepository;
import com.alextheracer1.portal2dpromax.db.UserRepository;
import com.alextheracer1.portal2dpromax.entities.score.Score;
import com.alextheracer1.portal2dpromax.entities.score.ScoreSaveRequest;
import com.alextheracer1.portal2dpromax.entities.user.Credentials;
import com.alextheracer1.portal2dpromax.entities.user.User;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final ScoreRepository scoreRepo;
  private final UserRepository userRepo;

  public Controller(ScoreRepository scoreRepo, UserRepository userRepo) {
    this.scoreRepo = scoreRepo;
    this.userRepo = userRepo;
  }

  @ApiResponse(responseCode = "200", description = "Returns all the scores")
  @GetMapping("/getScores")
  public ResponseEntity<List<Integer>> getScore() {
    var all = scoreRepo.findAll();
    List<Integer> scores = all.stream().map(Score::getScore).toList();
    return ResponseEntity.ok(scores);
  }

  @ApiResponse(responseCode = "200", description = "Returns a score for a UUID")
  @ApiResponse(responseCode = "400", description = "No Score for given UUID was found")
  @GetMapping("/getScores/{userId}")
  public ResponseEntity<String> getSpecificScore(@PathVariable String userId) {
    if (!scoreRepo.existsByUserId(userId)) {
      return ResponseEntity.badRequest().body("No score found");
    }
    var all = scoreRepo.findByUserId(userId);
    List<Integer> score = all.stream().map(Score::getScore).toList();
    return ResponseEntity.ok(score.toString());
  }

  @ApiResponse(responseCode = "200", description = "Saves a score to the database")
  @ApiResponse(responseCode = "400", description = "UserId is not valid")
  @PostMapping("/saveScore")
  public ResponseEntity<String> saveScore(@RequestBody ScoreSaveRequest ssr) {
    System.out.println("Score creation started...");

    if (!userRepo.existsByUserId(ssr.getUserId())) {
      return ResponseEntity.badRequest().body("User does not exist");
    }
    Score newScore = new Score(ssr.getUserId(), ssr.getScore());
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
  @PostMapping("/saveUser")
  public ResponseEntity<String> saveUser(@RequestBody Credentials credentials) {
    System.out.println("User creation started...");
    // uses google's hashing library to hash password into an SHA-256 hash
    credentials.hashPassword();

    String userId = UUID.randomUUID().toString();
    User user = new User(userId, credentials);

    userRepo.save(user);

    System.out.println("User creation complete...");

    return ResponseEntity.ok("User created");
  }
}
