package com.alextheracer1.portal2dpromax;

import com.alextheracer1.portal2dpromax.db.ScoreRepository;
import com.alextheracer1.portal2dpromax.entities.Score;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller {

    private ScoreRepository repo;

    public Controller(ScoreRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/test")
    public String getMessage() {
        createGroceryItems();
        var score = repo.findByName("Alex");
        if (score.isPresent()) {
            return score.get().toString();
        }
        return "No repo data found";
    }


    void createGroceryItems() {
        System.out.println("Data creation started...");
        Score score = new Score("Alex", 69);
        repo.save(score);
        System.out.println("Data creation complete...");
    }

}
