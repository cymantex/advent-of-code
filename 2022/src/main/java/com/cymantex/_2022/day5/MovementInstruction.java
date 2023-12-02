package com.cymantex._2022.day5;

public record MovementInstruction(int move, int from, int to) {

  /**
   * Example input: "move 10 from 3 to 5"
   */
  public static MovementInstruction from(String movementInstructionInput) {
    String[] instructions = movementInstructionInput.split(" ");

    return new MovementInstruction(
        Integer.parseInt(instructions[1]),
        Integer.parseInt(instructions[3]),
        Integer.parseInt(instructions[5]));
  }
}
