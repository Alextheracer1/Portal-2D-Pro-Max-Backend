package com.alextheracer1.portal2dpromax;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@AutoConfigureDataMongo
@WebMvcTest
@EnableMongoRepositories
@ContextConfiguration(initializers = Portal2DProMaxApplicationTests.Initializer.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Portal2DProMaxApplicationTests {

  @Container static MongoDBContainer mongo = new MongoDBContainer("mongo:5.0.5");

  @Autowired private MockMvc mockMvc;

  @Test
  @Order(1)
  public void saveUserTest() throws Exception {
    var request =
        post("/saveUser")
            .param("username", "something")
            .param("password", "test")
            .contentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(request).andExpect(status().isOk());
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext context) {
      TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
          context,
          String.format(
              "spring.data.mongodb.uri=mongodb://%s:%s",
              mongo.getHost(), mongo.getMappedPort(27017)));
    }
  }
}
