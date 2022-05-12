package com.alextheracer1.portal2dpromax.web.views;

import com.alextheracer1.portal2dpromax.api.entities.score.Score;
import com.alextheracer1.portal2dpromax.service.RestService;
import com.alextheracer1.portal2dpromax.web.StandardLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.List;

@Route
@RouteAlias(value = "/")
public class HomeView extends StandardLayout {

  public HomeView(RestService restService) {
    VerticalLayout content = new VerticalLayout();

    content.add("Hello World!");

    List<Score> scores = restService.getScores();

    for (Score score : scores) {
      content.add(score.toString() + "\n");
    }
    setContent(content);
  }
}
