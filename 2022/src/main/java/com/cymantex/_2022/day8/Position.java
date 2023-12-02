package com.cymantex._2022.day8;

import java.util.Optional;

public record Position(int x, int y) {

  @Override
  public String toString() {
    return String.format("(x: %s, y: %s)", x, y);
  }

  public Optional<Position> to(Direction direction, int maxX, int maxY) {
    Position next = switch (direction) {
      case EAST -> new Position(x + 1, y);
      case WEST -> new Position(x - 1, y);
      case NORTH -> new Position(x, y - 1);
      case SOUTH -> new Position(x, y + 1);
    };

    if (next.x < 0 || next.y < 0 || next.x >= maxX || next.y >= maxY) {
      return Optional.empty();
    }

    return Optional.of(next);
  }
}
