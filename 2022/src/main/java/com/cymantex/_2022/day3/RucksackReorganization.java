package com.cymantex._2022.day3;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class RucksackReorganization {

  public static int sumUnorganizedItemPriorities(String rucksacksList) {
    return findUnorganizedItems(toRucksackCompartments(rucksacksList))
        .stream()
        .mapToInt(RucksackReorganization::getPriority)
        .sum();
  }

  public static List<Pair<String, String>> toRucksackCompartments(String rucksacksList) {
    return rucksacksList
        .lines()
        .map(rucksack -> {
          int compartmentSize = rucksack.length() / 2;
          String compartment1 = rucksack.substring(0, compartmentSize);
          String compartment2 = rucksack.substring(compartmentSize);

          assert compartment1.length() == compartment2.length();

          return Pair.of(compartment1, compartment2);
        })
        .toList();
  }

  public static List<Character> findUnorganizedItems(List<Pair<String, String>> rucksackCompartments) {
    return rucksackCompartments
        .stream()
        .map(RucksackReorganization::findUnorganizedItem)
        .toList();
  }

  public static Character findUnorganizedItem(Pair<String, String> rucksackCompartment) {
    return rucksackCompartment
        .getLeft()
        .chars()
        .mapToObj(item -> String.valueOf((char) item))
        .filter(rucksackCompartment.getRight()::contains)
        .map(itemString -> itemString.charAt(0))
        .findFirst()
        .orElseThrow();
  }

  public static int getPriority(Character item) {
    String itemString = item.toString();

    if (itemString.matches("[a-z]")) {
      // a-z should have a priority between 0-26. 'a' has unicode 97.
      return itemString.codePointAt(0) - 96;
    }

    // A-Z should have a priority between 27-52. 'A' has unicode 65.
    return itemString.codePointAt(0) - 38;
  }
}
