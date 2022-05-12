package com.alextheracer1.portal2dpromax.web.views;

import com.alextheracer1.portal2dpromax.api.entities.score.Score;
import com.alextheracer1.portal2dpromax.api.entities.score.Score.ScorePresentation;
import com.alextheracer1.portal2dpromax.service.RestService;
import com.alextheracer1.portal2dpromax.web.StandardLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.List;

@Route
@RouteAlias(value = "/leaderboard")
public class LeaderboardView extends StandardLayout {

  public LeaderboardView(RestService restService) {
    header();
    leaderboard(restService);
  }

  private void header() {
    H1 leaderboard = new H1("Leaderboard");
    this.setJustifyContentMode(JustifyContentMode.CENTER);
    this.setAlignItems(Alignment.CENTER);
    add(leaderboard);
  }

  private void leaderboard(RestService restService) {
    Grid<Score.ScorePresentation> grid = new Grid<>(Score.ScorePresentation.class, false);

    List<ScorePresentation> scores =
        restService.getScores().stream()
            .limit(10)
            .map(score -> score.toPresentation(restService))
            .toList();

    grid.addColumn(ScorePresentation::getUsername).setHeader("Username").setSortable(true);
    grid.addColumn(ScorePresentation::getScore).setHeader("Score").setSortable(true);

    grid.setItems(scores);
    grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
    add(grid);
  }
}
