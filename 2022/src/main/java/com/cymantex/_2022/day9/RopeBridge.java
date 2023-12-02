package com.cymantex._2022.day9;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RopeBridge {

  private final Map<Integer, LinkedList<Position>> snakePartPositions;
  private final Deque<MovementInstruction> movementInstructions;
  private final int snakeSize;

  public RopeBridge(String movementInstructionsInput, int snakeSize) {
    movementInstructions = toMovementInstructions(movementInstructionsInput);
    snakePartPositions = new HashMap<>();
    this.snakeSize = snakeSize;

    for (int snakePartId = 0; snakePartId < snakeSize; snakePartId++) {
      snakePartPositions.put(snakePartId, new LinkedList<>());
      snakePartPositions
          .computeIfAbsent(snakePartId, position -> new LinkedList<>())
          .add(new Position(0, 0));
    }
  }

  public long getUniqueTailPositionsSize() {
    int tailId = snakePartPositions.keySet().size() - 1;
    return new HashSet<>(snakePartPositions.get(tailId)).size();
  }

  public RopeBridge traverse() {
    while (!movementInstructions.isEmpty()) {
      follow(movementInstructions.pop());
    }

    for (int snakePartId = 0; snakePartId < snakeSize; snakePartId++) {
      new MapPrinter(snakePartPositions.get(snakePartId)).printMap(Integer.toString(snakePartId));
    }

    return this;
  }

  private void follow(MovementInstruction movementInstruction) {
    for (int i = 0; i < movementInstruction.steps(); i++) {
      updateTailPositions(movementInstruction);
      headPositions().add(headPositions().getLast().to(movementInstruction.direction()));
    }
  }

  private void updateTailPositions(MovementInstruction movementInstruction) {
    Map<Integer, Position> updatedPositions = new HashMap<>();
    Position current = headPositions().getLast();
    Position next = current.to(movementInstruction.direction());

    for (int snakePartId = 1; snakePartId < snakeSize; snakePartId++) {
      if (!tailIsTouching(next, snakePartId)) {
        updatedPositions.put(snakePartId, current);
      }

      next = new Position(current.x(), current.y());
      current = snakePartPositions.get(snakePartId).getLast();
    }

    updatedPositions.forEach((i, p) -> snakePartPositions.get(i).add(p));
  }

  private void traverseTail(Consumer<LinkedList<Position>> consumeTailPositions) {
    for (int i = 1; i < snakeSize; i++) {
      consumeTailPositions.accept(snakePartPositions.get(i));
    }
  }

  private LinkedList<Position> headPositions() {
    return snakePartPositions.get(0);
  }

  private boolean tailIsTouching(Position headPosition, Integer snakePartId) {
    return snakePartPositions.get(snakePartId).getLast().isTouching(headPosition);
  }

  private Deque<MovementInstruction> toMovementInstructions(String movementInstructionsInput) {
    return movementInstructionsInput.lines()
        .map(movementInstruction -> movementInstruction.split(" "))
        .map(movementInstruction -> new MovementInstruction(
            Direction.valueOf(movementInstruction[0]),
            Integer.parseInt(movementInstruction[1])))
        .collect(Collectors.toCollection(ArrayDeque::new));
  }
}
