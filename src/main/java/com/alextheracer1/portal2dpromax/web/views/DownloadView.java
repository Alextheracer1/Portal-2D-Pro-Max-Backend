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
@RouteAlias(value = "/download")
public class DownloadView extends StandardLayout {


  public DownloadView() {
    VerticalLayout content = new VerticalLayout();

    H1 h1 = new H1("Download Portal2DProMax");
    content.setAlignItems(Alignment.CENTER);
    content.add(h1);

    Button button = new Button("Download");
    button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    button.setIcon(VaadinIcon.DOWNLOAD.create());
    button.addClickListener(e -> getUI().get().getPage().setLocation("https://github.com/Alextheracer1/Portal-2D-Pro-Max/releases/latest"));
    content.add(button);


    setContent(content);
  }

}
