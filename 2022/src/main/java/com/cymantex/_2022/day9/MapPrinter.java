package com.cymantex._2022.day9;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class MapPrinter {
  private final List<Position> positions;

  public MapPrinter(List<Position> positions) {
    this.positions = positions;
  }

  public void printMap(String snakeSymbol) {
    LinkedHashMap<Position, String> map = new LinkedHashMap<>();

    iterateMap(position -> map.put(position, "."), () -> {});

    for (Position position : positions) {
      map.put(position, snakeSymbol);
    }
    map.put(positions.get(0), "S");

    iterateMap(position -> System.out.print(map.get(position)), System.out::println);
    System.out.println();
  }

  private void iterateMap(Consumer<Position> positionConsumer, Runnable onRowEnd) {
    int offset = 2;
    int highestX = highestX() + offset;
    int highestY = highestY() + offset;
    int lowestX = lowestX() - offset;
    int lowestY = lowestY() - offset;

    for (int y = lowestY; y < highestY; y++) {
      for (int x = lowestX; x < highestX; x++) {
        positionConsumer.accept(new Position(x, y));
      }
      onRowEnd.run();
    }
  }

  private int highestX() {
    return findPosition(Position::x, IntStream::max);
  }

  private int lowestX() {
    return findPosition(Position::x, IntStream::min);
  }

  private int highestY() {
    return findPosition(Position::y, IntStream::max);
  }

  private int lowestY() {
    return findPosition(Position::y, IntStream::min);
  }

  private int findPosition(
      ToIntFunction<Position> mapToInt,
      Function<IntStream, OptionalInt> reducer) {
    IntStream intStream = positions.stream().mapToInt(mapToInt);
    return reducer.apply(intStream).orElseThrow();
  }
}
