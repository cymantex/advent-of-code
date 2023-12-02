package com.cymantex._2022.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Monkey {
  private final int id;
  private final List<Item> items;
  private final Operation operation;
  private final DivisibleTest divisibleTest;
  private int numberOfInspectedItems;

  public Monkey(int id, List<Item> items, Operation operation, DivisibleTest divisibleTest) {
    this.id = id;
    this.items = items;
    this.operation = operation;
    this.divisibleTest = divisibleTest;
    numberOfInspectedItems = 0;
  }

  /**
   * <pre>
   * Monkey 0:
   *   Starting items: 79, 98
   *   Operation: new = old * 19
   *   Test: divisible by 23
   *     If true: throw to monkey 2
   *     If false: throw to monkey 3
   * </pre>
   */
  public static Monkey from(String monkeyInput) {
    List<String> lines = monkeyInput.lines().toList();

    int id = toId(lines.get(0));
    List<Item> startingItems = new ArrayList<>(toStartingItems(lines.get(1)));
    Operation operation = Operation.from(lines.get(2));
    DivisibleTest divisibleTest = DivisibleTest.from(lines.get(3), lines.get(4), lines.get(5));

    return new Monkey(id, startingItems, operation, divisibleTest);
  }

  public List<ItemThrow> getItemsMonkeyWantsToThrow(ItemWorryLevelOperation itemWorryLevelOperation) {
    return items.stream()
        .map(item -> {
          itemWorryLevelOperation.accept(operation, item);
          int nextMonkeyId = divisibleTest.nextMonkeyId(item);
          return new ItemThrow(id, nextMonkeyId, item);
        })
        .toList();
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item itemToRemove) {
    items.removeIf(item -> item.equals(itemToRemove));
  }

  public void incrementInspectedItems() {
    numberOfInspectedItems = numberOfInspectedItems + 1;
  }

  public int getNumberOfInspectedItems() {
    return numberOfInspectedItems;
  }

  public int id() {
    return id;
  }

  public List<Item> items() {
    return items;
  }

  private static int toId(String monkeyIdInput) {
    return Integer.parseInt(monkeyIdInput.split(" ")[1].replace(":", ""));
  }

  private static List<Item> toStartingItems(String startingItemsInput) {
    return Arrays
        .stream(startingItemsInput.split(" "))
        .filter(string -> string.length() > 0)
        .filter(string -> string.substring(0, 1).matches("[0-9]"))
        .map(number -> number.replace(",", ""))
        .map(Integer::parseInt)
        .map(Item::new)
        .toList();
  }
}
