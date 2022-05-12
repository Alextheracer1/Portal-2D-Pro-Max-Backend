package com.alextheracer1.portal2dpromax.web;

import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class StandardLayout extends VerticalLayout {

  private final HorizontalLayout navBar = new HorizontalLayout();
  private final VerticalLayout content = new VerticalLayout();
  private final Footer footer = new Footer();

  public StandardLayout() {
    setSizeFull();
    addNavBar();
    addFooter();
    add(navBar, content, footer);
  }

  private void addFooter() {
    footer.add("This is a footer");
  }

  private void addNavBar() {
    navBar.add("This is a navbar");
  }

  protected void setContent(VerticalLayout content) {
    this.content.removeAll();
    this.content.add(content);
  }




}
