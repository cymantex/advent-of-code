package com.cymantex._2022.day11;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class Operation {

  private final String arg1;
  private final String arg2;
  private final String operator;

  public Operation(String arg1, String arg2, String operator) {
    this.arg1 = arg1;
    this.arg2 = arg2;
    this.operator = operator;
  }

  /**
   * Sample Input: Operation: new = old * old
   */
  public static Operation from(String operationInput) {
    List<String> operation = Arrays
        .stream(operationInput.split(" "))
        .dropWhile(string -> !string.contains("="))
        .filter(string -> !string.equals("="))
        .toList();

    return new Operation(operation.get(0), operation.get(2), operation.get(1));
  }

  public void calcReducedWorryLevel(Item item) {
    int reducedWorryLevel = (int) Math.floor((double) calcWorryLevel(item) / 3);
    item.setWorryLevel(reducedWorryLevel);
  }

  public void delayedWorryLevelCalc(Item item) {
    WorryLevelOperations operations = item.getWorryLevelOperations();

    if (operator.equals("*")) {
      getArgument().ifPresentOrElse(operations::addAddend, operations::addSelfAsAddend);
    } else {
      getArgument().ifPresentOrElse(operations::addMultiplier, operations::addSelfAsMultiplier);
    }
  }

  private int calcWorryLevel(Item item) {
    int arg1 = getArg1(item);
    int arg2 = getArg2(item);
    return operator.equals("*") ? arg1 * arg2 : arg1 + arg2;
  }

  private int getArg2(Item item) {
    return "old".equals(arg2) ? item.getWorryLevel() : Integer.parseInt(arg2);
  }

  private int getArg1(Item item) {
    return "old".equals(arg1) ? item.getWorryLevel() : Integer.parseInt(arg1);
  }

  private Optional<Integer> getArgument() {
    if ("old".equals(arg1) && "old".equals(arg2)) {
      // TODO: Should be with the addend and multipliers...
      return Optional.empty();
    }

    return Optional.of("old".equals(arg1) ? Integer.parseInt(arg2) : Integer.parseInt(arg1));
  }
}
