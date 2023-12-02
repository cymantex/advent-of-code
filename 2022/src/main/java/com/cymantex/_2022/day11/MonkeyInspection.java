package com.cymantex._2022.day11;

import com.cymantex._2022.Logger;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class MonkeyInspection {

  private final MonkeyMap monkeyMap;
  private final ItemWorryLevelOperation calcWorryLevel;

  public MonkeyInspection(String monkeysInput, ItemWorryLevelOperation calcWorryLevel) {
    this.monkeyMap = MonkeyMap.from(monkeysInput);
    this.calcWorryLevel = calcWorryLevel;
  }

  public String calcMonkeyBusiness() {
    monkeyMap.monkeys().forEach(monkey -> Logger.info(() ->
        System.out.printf("Money %d: %d \n", monkey.id(), monkey.getNumberOfInspectedItems())));

    Deque<Integer> inspectedItems = monkeyMap.monkeys().stream()
        .map(Monkey::getNumberOfInspectedItems)
        .sorted()
        .collect(Collectors.toCollection(ArrayDeque::new));

    return new BigInteger(Integer.toString(inspectedItems.removeLast()))
        .multiply(new BigInteger(Integer.toString(inspectedItems.removeLast())))
        .toString();
  }

  public MonkeyInspection runItemInspectionRounds(int numberOfRounds) {
    for (int round = 1; round <= numberOfRounds; round++) {
      Logger.debug(round, _round -> System.out.printf("Round %s\n", _round));
      runItemInspectionRound();
      Logger.debug(System.out::println);
    }

    return this;
  }

  private void runItemInspectionRound() {
    monkeyMap.monkeys().forEach(monkey -> monkey
        .getItemsMonkeyWantsToThrow(calcWorryLevel)
        .forEach(monkeyMap::handleItemThrow));

    monkeyMap.monkeys().forEach(monkey ->
        Logger.debug(() -> System.out.printf("Money %d: %s \n", monkey.id(), monkey.items())));
  }
}
