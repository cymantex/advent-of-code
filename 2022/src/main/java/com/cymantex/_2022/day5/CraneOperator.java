package com.cymantex._2022.day5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class CraneOperator {
  private final Deque<MovementInstruction> movementInstructions;

  public CraneOperator(String supplyStacksAndMovementInstructions) {
    movementInstructions = supplyStacksAndMovementInstructions.lines()
        .dropWhile(line -> !line.isBlank())
        .filter(line -> !line.isBlank())
        .map(MovementInstruction::from)
        .collect(Collectors.toCollection(ArrayDeque::new));
  }

  public String operate(CrateMover crateMover) {
    movementInstructions.forEach(crateMover::move);
    return crateMover.topCrateNames();
  }
}
