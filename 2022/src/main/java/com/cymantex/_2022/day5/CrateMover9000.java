package com.cymantex._2022.day5;

public class CrateMover9000 implements CrateMover {
  protected final SupplyStacks supplyStacks;

  public CrateMover9000(String supplyStacksAndMovementInstructions) {
    supplyStacks = SupplyStacks.from(supplyStacksAndMovementInstructions);
  }

  @Override
  public String topCrateNames() {
    return String.join("", supplyStacks.getTopCrates());
  }

  @Override
  public void move(MovementInstruction movementInstruction) {
    for (int i = 0; i < movementInstruction.move(); i++) {
      supplyStacks.push(movementInstruction.to(), supplyStacks.pop(movementInstruction.from()));
    }
  }
}
