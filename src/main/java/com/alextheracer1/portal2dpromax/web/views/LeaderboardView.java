package com.alextheracer1.portal2dpromax.web.views;


import com.alextheracer1.portal2dpromax.api.entities.score.Score;
import com.alextheracer1.portal2dpromax.api.entities.user.Credentials;
import com.alextheracer1.portal2dpromax.service.RestService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.List;
import java.util.stream.Stream;

@Route
@RouteAlias(value = "/leaderboard/")
public class LeaderboardView extends VerticalLayout {


  public LeaderboardView(RestService restService) {
    leaderboard(restService);
  }

  private void leaderboard(RestService restService) {
    Grid<Score> grid = new Grid<>(Score.class, false);
    grid.addColumn(Score::getUserId).setHeader("UserID").setSortable(true);
    grid.addColumn(Score::getScore).setHeader("Score").setSortable(true);

    List<Score> scores = restService.getScores();
    grid.setItems(scores);


    List<Credentials> username = restService.getUsernames();
    grid.addColumn(item -> username).setHeader("Username").setSortable(true);
    grid.setItems(username);


    add(grid);
  }
}
