package com.cymantex._2022.day10;

public class CycleRegister {
  private final int cycle;

  private final int register;

  private Instruction instruction;
  public CycleRegister(int cycle, int register) {
    this.cycle = cycle;
    this.register = register;
  }

  private CycleRegister(int cycle, int register, Instruction instruction) {
    this.cycle = cycle;
    this.register = register;
    this.instruction = instruction;
  }

  public int getWorkingCycle() {
    return cycle - 1;
  }

  public int getCycle() {
    return cycle;
  }

  public int getRegister() {
    return register;
  }

  public int calcSignalStrength() {
    return cycle * register;
  }

  public CycleRegister add(int number) {
    return new CycleRegister(cycle, register + number, instruction);
  }

  public CycleRegister increment() {
    return new CycleRegister(cycle + 1, register, instruction);
  }

  public void setInstruction(Instruction instruction) {
    this.instruction = instruction;
  }
}
