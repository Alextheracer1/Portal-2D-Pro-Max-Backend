package com.alextheracer1.portal2dpromax.web.views;

import com.alextheracer1.portal2dpromax.web.StandardLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route
@RouteAlias(value = "/")
public class HomeView extends StandardLayout {

  public HomeView() {
    VerticalLayout content = new VerticalLayout();

    H1 h1 = new H1("Welcome to the official Portal2DProMax website!");
    content.setAlignItems(Alignment.CENTER);
    content.add(h1);
    content.add("Here you can download the game, or look at the leaderboard.");
    Button button = new Button("Download");

    button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    button.setIcon(VaadinIcon.DOWNLOAD.create());
    button.addClickListener(e -> getUI().get().getPage().setLocation("/download"));

    content.add(button);

    setContent(content);
  }
}
