package io.pipecafts.jooq;

import org.springframework.boot.SpringApplication;

public class TestJooqDemoApplication {

  public static void main(String[] args) {
    SpringApplication.from(JooqDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
  }

}
