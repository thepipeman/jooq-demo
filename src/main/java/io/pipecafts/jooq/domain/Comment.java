package io.pipecafts.jooq.domain;

import java.time.LocalDateTime;

public record Comment(
  Long id,
  String name,
  String content,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {
}
