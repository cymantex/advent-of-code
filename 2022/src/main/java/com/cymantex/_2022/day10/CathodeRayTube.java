package com.cymantex._2022.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CathodeRayTube implements Iterator<List<CycleRegister>> {
  private final Deque<Instruction> instructions;
  private CycleRegister cycleRegister;

  public CathodeRayTube(String instructions) {
    this.instructions = instructions.lines()
        .map(Instruction::from)
        .collect(Collectors.toCollection(ArrayDeque::new));
    cycleRegister = new CycleRegister(0, 1);
  }

  @Override
  public boolean hasNext() {
    return instructions.size() > 0;
  }

  @Override
  public List<CycleRegister> next() {
    return execute(instructions.pop());
  }

  private List<CycleRegister> execute(Instruction instruction) {
    cycleRegister.setInstruction(instruction);

    return switch (instruction.operation()) {
      case addx -> addx(instruction.argument());
      case noop -> noop();
    };
  }

  private List<CycleRegister> noop() {
    return List.of(incrementCycleRegister());
  }

  private List<CycleRegister> addx(int number) {
    List<CycleRegister> cycleRegisters = new ArrayList<>();

    for (int cycle = 0; cycle < 2; cycle++) {
      cycleRegisters.add(incrementCycleRegister());

      if (cycle == 1) {
        cycleRegister = cycleRegister.add(number);
      }
    }

    return cycleRegisters;
  }

  private CycleRegister incrementCycleRegister() {
    this.cycleRegister = cycleRegister.increment();
    return cycleRegister;
  }
}
