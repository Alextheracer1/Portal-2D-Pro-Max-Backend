package com.alextheracer1.portal2dpromax.service;

import com.alextheracer1.portal2dpromax.api.entities.score.Score;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

  private static final Logger log = LoggerFactory.getLogger(RestService.class);
  private final RestTemplate restTemplate;

  public RestService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public List<Score> getScores() throws RestClientException {
    VaadinServletRequest request = (VaadinServletRequest) VaadinService.getCurrentRequest();
    String host = request.getHttpServletRequest().getRequestURL().toString();
    String url = host + "api/getScores";

    return exchangeAsList(url, new ParameterizedTypeReference<>() {
    });
  }

  public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
    return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
  }
}
