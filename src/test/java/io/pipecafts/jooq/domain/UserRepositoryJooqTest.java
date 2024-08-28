package io.pipecafts.jooq.domain;


import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@JooqTest(
//  properties = {
//    "spring.test.database.replace=none",
//    "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
//  }
//)
//@Sql("/test-data.sql")
@SpringBootTest
@Sql("/test-data.sql")
@Testcontainers
public class UserRepositoryJooqTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
    "postgres:16-alpine"
  );

  @Autowired
  UserRepository repository;

  @Test
  void shouldCreateUserSuccessfully() {
    User user = new User(null, "John", "john@gmail.com");

    User savedUser = repository.create(user);

    assertThat(savedUser.id()).isNotNull();
    assertThat(savedUser.name()).isEqualTo("John");
    assertThat(savedUser.email()).isEqualTo("john@gmail.com");
  }

  @Test
  void shouldGetUserByEmail() {
    User user = repository.getUserByEmail("siva@gmail.com").orElseThrow();

    assertThat(user.id()).isEqualTo(1L);
    assertThat(user.name()).isEqualTo("Siva");
    assertThat(user.email()).isEqualTo("siva@gmail.com");
  }
}
