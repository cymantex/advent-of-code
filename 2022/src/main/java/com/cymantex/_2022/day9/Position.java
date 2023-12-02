package com.cymantex._2022.day9;

import java.util.List;

public record Position(int x, int y) {
  public Position to(Direction direction) {
    return switch (direction) {
      case R -> new Position(x + 1, y);
      case L -> new Position(x - 1, y);
      case U -> new Position(x, y - 1);
      case D -> new Position(x, y + 1);
    };
  }

  public boolean isTouching(Position position) {
    return neighboursIncludingSelf()
        .stream()
        .anyMatch(neighbour -> neighbour.equals(position));
  }

  public List<Position> neighboursIncludingSelf() {
    return List.of(
        new Position(x + 1, y),
        new Position(x + 1, y + 1),
        new Position(x + 1, y - 1),
        new Position(x - 1, y),
        new Position(x - 1, y + 1),
        new Position(x - 1, y - 1),
        new Position(x, y + 1),
        new Position(x, y - 1),
        new Position(x, y)
    );
  }
}
