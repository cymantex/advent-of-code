package com.cymantex._2022.day5;

import java.util.ArrayDeque;
import java.util.Deque;

public class CrateMover9001 implements CrateMover {
  protected final SupplyStacks supplyStacks;

  public CrateMover9001(String supplyStacksAndMovementInstructions) {
    supplyStacks = SupplyStacks.from(supplyStacksAndMovementInstructions);
  }

  @Override
  public String topCrateNames() {
    return String.join("", supplyStacks.getTopCrates());
  }

  @Override
  public void move(MovementInstruction movementInstruction) {
    Deque<Crate> cratesToMove = new ArrayDeque<>();

    for (int i = 0; i < movementInstruction.move(); i++) {
      cratesToMove.addFirst(supplyStacks.pop(movementInstruction.from()));
    }

    cratesToMove.forEach(crate -> supplyStacks.push(movementInstruction.to(), crate));
  }
}
