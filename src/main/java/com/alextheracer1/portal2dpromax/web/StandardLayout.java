package com.alextheracer1.portal2dpromax.web;

import com.alextheracer1.portal2dpromax.web.views.HomeView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./css/styles.css")
public class StandardLayout extends VerticalLayout {

  private final HorizontalLayout navBar = new HorizontalLayout();
  private final VerticalLayout content = new VerticalLayout();
  private final Footer footer = new Footer();

  public StandardLayout() {
    content.setSizeFull();
    setSpacing(false);
    setPadding(false);
    setHeightFull();
    addNavBar();
    addFooter();
    add(navBar, content, footer);
  }

  private void addFooter() {
    footer.add("This is a footer");
  }

  private void addNavBar() {
    navBar.setId("navbar");
    MenuBar menuBar = new MenuBar();

    navBar.setSizeFull();
    navBar.setMinHeight("3.2rem");
    navBar.setMaxHeight("3.2rem");
    //navBar.setJustifyContentMode(JustifyContentMode.CENTER);

    H1 title = new H1("Portal2DProMax");
    menuBar.addItem(title);

    if (!(this instanceof HomeView)) {
      Button homeButton = new Button("HOME");
      homeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
      homeButton.setIcon(VaadinIcon.HOME.create());
      homeButton.addClickListener(e -> getUI().get().getPage().setLocation("/"));
      navBar.add(homeButton);
      menuBar.addItem(homeButton);
    } else {
      Button leaderboardButton = new Button("LEADERBOARD");
      leaderboardButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
      leaderboardButton.setIcon(VaadinIcon.LIST_OL.create());
      leaderboardButton.addClickListener(e -> getUI().get().getPage().setLocation("/leaderboard"));
      menuBar.addItem(leaderboardButton);
    }
    menuBar.setId("menuBar");
    menuBar.setWidthFull();
    navBar.setSpacing(true);
    navBar.add(menuBar);
  }

  protected void setContent(VerticalLayout content) {
    this.content.removeAll();
    this.content.add(content);
  }
}
