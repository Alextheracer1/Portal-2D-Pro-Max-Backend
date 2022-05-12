package com.alextheracer1.portal2dpromax.web.router;

import com.alextheracer1.portal2dpromax.web.views.PageNotFoundView;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.RouteNotFoundError;
import javax.servlet.http.HttpServletResponse;

public class PageNotFoundRouter extends RouteNotFoundError {

  @Override
  public int setErrorParameter(
      BeforeEnterEvent event, ErrorParameter<NotFoundException> errorParameter) {
    event.rerouteTo(PageNotFoundView.class);
    return HttpServletResponse.SC_NOT_FOUND;
  }
}
