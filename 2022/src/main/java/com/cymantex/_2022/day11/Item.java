package com.cymantex._2022.day11;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Item {

  private final UUID id;
  private final WorryLevelOperations worryLevelOperations;
  private int worryLevel;

  public Item(int worryLevel) {
    id = UUID.randomUUID();
    this.worryLevel = worryLevel;
    worryLevelOperations = new WorryLevelOperations();
  }

  public WorryLevelOperations getWorryLevelOperations() {
    return worryLevelOperations;
  }

  public List<Item> getOldWorryLevelMultipliers() {
    return oldWorryLevelMultipliers;
  }

  public List<Item> getOldWorryLevelAddends() {
    return oldWorryLevelAddends;
  }

  public List<Integer> getWorryLevelAddends() {
    return worryLevelAddends;
  }

  public List<Integer> getWorryLevelMultipliers() {
    return worryLevelMultipliers;
  }

  public void addWorryLevelAddend(Integer addend) {
    worryLevelAddends.add(addend);
  }

  public int getWorryLevel() {
    return worryLevel;
  }

  public void setWorryLevel(int worryLevel) {
    this.worryLevel = worryLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item that)) {
      return false;
    }
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
