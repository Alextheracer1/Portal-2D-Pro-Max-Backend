package com.alextheracer1.portal2dpromax.web.views;

import com.alextheracer1.portal2dpromax.web.StandardLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route
@RouteAlias(value = "/404")
public class PageNotFoundView extends StandardLayout {

  public PageNotFoundView() {
    VerticalLayout content = new VerticalLayout();
    notFound(content);
  }

  private void notFound(VerticalLayout content) {
    H1 h1 = new H1("404");
    H2 h2 = new H2("Page not found");
    content.add(h1);
    content.add(h2);
    setContent(content);
  }
}
