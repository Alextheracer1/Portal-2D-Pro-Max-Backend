package com.alextheracer1.portal2dpromax.web.views;

import com.alextheracer1.portal2dpromax.web.StandardLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route
@RouteAlias(value = "/404")
public class PageNotFoundView extends StandardLayout {

  public PageNotFoundView() {
    notFound();
  }

  private void notFound() {
    H1 h1 = new H1("404");
    H2 h2 = new H2("Page not found");
    add(h1);
    add(h2);
  }
}
