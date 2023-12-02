package com.cymantex._2022.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CathodeAnalyzer {
  public static void drawCrt(String instructions) {
    readCycleRegisters(instructions).forEach(cycleRegister -> {
      System.out.print(isSprite(cycleRegister) ? "#" : ".");

      if (cycleRegister.getCycle() % 40 == 0) {
        System.out.println();
      }
    });
  }

  public static int sumSignalStrengths(String instructions, Set<Integer> cycles) {
    return readCycleRegisters(instructions)
        .stream()
        .filter(cycleRegister -> cycles.contains(cycleRegister.getCycle()))
        .mapToInt(CycleRegister::calcSignalStrength)
        .sum();
  }

  private static boolean isSprite(CycleRegister cycleRegister) {
    int register = cycleRegister.getRegister();
    int cycle = cycleRegister.getWorkingCycle() % 40;

    return register == cycle || register == cycle + 1 || register == cycle - 1;
  }

  private static List<CycleRegister> readCycleRegisters(String instructions) {
    List<CycleRegister> registers = new ArrayList<>();
    CathodeRayTube cathodeRayTube = new CathodeRayTube(instructions);

    while (cathodeRayTube.hasNext()) {
      registers.addAll(cathodeRayTube.next());
    }

    return registers;
  }
}
