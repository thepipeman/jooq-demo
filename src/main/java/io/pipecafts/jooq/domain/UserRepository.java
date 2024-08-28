package io.pipecafts.jooq.domain;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.testcontainers.demo.jooq.tables.Users.USERS;
import static org.jooq.Records.mapping;

@Repository
class UserRepository {

  private final DSLContext dsl;

  UserRepository(DSLContext dslContext) {
    this.dsl = dslContext;
  }

  public User create(User user) {
    return this.dsl.insertInto(USERS)
      .set(USERS.NAME, user.name())
      .set(USERS.EMAIL, user.email())
      .set(USERS.CREATED_AT, LocalDateTime.now())
      .returningResult(USERS.ID, USERS.NAME, USERS.EMAIL)
      .fetchAny(mapping(User::new));
  }

  public Optional<User> getUserByEmail(String email) {
    return this.dsl.select(USERS.ID, USERS.NAME, USERS.EMAIL)
      .from(USERS)
      .where(USERS.EMAIL.equalIgnoreCase(email))
      .fetchOptional(mapping(User::new));
  }
}
