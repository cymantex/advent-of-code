package com.cymantex._2022.day5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SupplyStacks {
  private final Map<Integer, ArrayDeque<Crate>> supplyStacks;

  SupplyStacks(Map<Integer, ArrayDeque<Crate>> supplyStacks) {
    this.supplyStacks = supplyStacks;
  }

  List<String> getTopCrates() {
    return supplyStacks.values()
        .stream()
        .map(ArrayDeque::peek)
        .filter(Objects::nonNull)
        .map(Crate::name)
        .toList();
  }

  Crate pop(int column) {
    return supplyStacks.get(column).pop();
  }

  void push(int column, Crate crate) {
    supplyStacks.get(column).push(crate);
  }

  static SupplyStacks from(String instructions) {
    LinkedList<String> supplyStacksLines = extractReversedSupplyStackLines(instructions);
    List<Integer> columnIndexes = extractColumnIndexes(supplyStacksLines);
    return new SupplyStacks(toSupplyStacks(supplyStacksLines, columnIndexes));
  }

  private static Map<Integer, ArrayDeque<Crate>> toSupplyStacks(
      List<String> supplyStacksLines, List<Integer> columnIndexes) {
    Map<Integer, ArrayDeque<Crate>> supplyStacks = new HashMap<>();

    supplyStacksLines.forEach(supplyStackLine -> columnIndexes.forEach(columnIndex -> {
      char crate = supplyStackLine.charAt(columnIndex);
      if (Character.isAlphabetic(crate)) {
        supplyStacks
            .computeIfAbsent(columnIndexes.indexOf(columnIndex) + 1, i -> new ArrayDeque<>())
            .push(new Crate(String.valueOf(crate)));
      }
    }));

    return supplyStacks;
  }

  private static LinkedList<String> extractReversedSupplyStackLines(String instructions) {
    LinkedList<String> supplyStackLines = instructions
        .lines()
        .takeWhile(line -> !line.isBlank())
        .collect(Collectors.toCollection(LinkedList::new));
    Collections.reverse(supplyStackLines);
    return supplyStackLines;
  }

  private static List<Integer> extractColumnIndexes(LinkedList<String> supplyStackLines) {
    List<Integer> columns = new ArrayList<>();
    char[] columnLabels = supplyStackLines.removeFirst().toCharArray();

    for (int i = 0; i < columnLabels.length; i++) {
      if (Character.isDigit(columnLabels[i])) {
        columns.add(i);
      }
    }

    return columns;
  }
}
