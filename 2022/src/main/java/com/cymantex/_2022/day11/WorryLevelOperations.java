package com.cymantex._2022.day11;

import java.util.ArrayList;
import java.util.List;

public class WorryLevelOperations {

  private final List<WorryLevelOperations> worryLevelAddends;
  private final List<WorryLevelOperations> worryLevelMultipliers;
  private final List<Integer> addends;
  private final List<Integer> multipliers;

  public WorryLevelOperations() {
    this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  }

  public WorryLevelOperations(List<WorryLevelOperations> worryLevelAddends,
      List<WorryLevelOperations> worryLevelMultipliers, List<Integer> addends,
      List<Integer> multipliers) {
    this.worryLevelAddends = worryLevelAddends;
    this.worryLevelMultipliers = worryLevelMultipliers;
    this.addends = addends;
    this.multipliers = multipliers;
  }

  public List<WorryLevelOperations> getWorryLevelAddends() {
    return worryLevelAddends;
  }

  public List<WorryLevelOperations> getWorryLevelMultipliers() {
    return worryLevelMultipliers;
  }

  public List<Integer> getAddends() {
    return addends;
  }

  public List<Integer> getMultipliers() {
    return multipliers;
  }

  public void addAddend(Integer addend) {
    addends.add(addend);
  }

  public void addSelfAsAddend() {
    worryLevelAddends.add(copy());
  }

  public void addMultiplier(Integer multiplier) {
    multipliers.add(multiplier);
  }

  public void addSelfAsMultiplier() {
    worryLevelMultipliers.add(copy());
  }

  private WorryLevelOperations copy() {
    return new WorryLevelOperations(worryLevelAddends, worryLevelMultipliers,
        addends, multipliers);
  }
}
