package com.cymantex._2022.day11;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public class MonkeyMap {

  private final LinkedHashMap<Integer, Monkey> monkeyMap;

  private MonkeyMap(LinkedHashMap<Integer, Monkey> monkeyMap) {
    this.monkeyMap = monkeyMap;
  }

  public Collection<Monkey> monkeys() {
    return monkeyMap.values();
  }

  public void handleItemThrow(ItemThrow itemThrow) {
    Monkey throwingMonkey = monkeyMap.get(itemThrow.throwingMonkeyId());
    throwingMonkey.incrementInspectedItems();
    throwingMonkey.removeItem(itemThrow.item());
    monkeyMap.get(itemThrow.receivingMonkeyId()).addItem(itemThrow.item());
  }

  public static MonkeyMap from(String monkeysInput) {
    List<Monkey> monkeys = Arrays
        .stream(monkeysInput.split("\n\n"))
        .map(Monkey::from)
        .toList();

    LinkedHashMap<Integer, Monkey> monkeyMap = new LinkedHashMap<>();
    monkeys.forEach(monkey -> monkeyMap.put(monkey.id(), monkey));

    return new MonkeyMap(monkeyMap);
  }
}
