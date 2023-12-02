package com.cymantex._2022.day10;

import static com.cymantex._2022.day10.Operation.noop;

public record Instruction(Operation operation, Integer argument) {
  public static Instruction from(String instructionLine) {
    String[] instructions = instructionLine.split(" ");
    Operation operation = Operation.valueOf(instructions[0]);
    Integer argument = noop.equals(operation) ? null : Integer.parseInt(instructions[1]);

    return new Instruction(operation, argument);
  }
}
