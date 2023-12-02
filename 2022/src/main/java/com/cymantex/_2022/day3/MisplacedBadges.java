package com.cymantex._2022.day3;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Triple;

public class MisplacedBadges {

  public static int sumMisplacedBadgePriorities(String rucksacksList) {
    return findMisplacedBadges(toElfGroups(rucksacksList))
        .stream()
        .mapToInt(RucksackReorganization::getPriority)
        .sum();
  }

  public static List<Character> findMisplacedBadges(List<Triple<String, String, String>> elfGroups) {
    return elfGroups.stream().map(MisplacedBadges::findMisplacedBadge).toList();
  }

  private static List<Triple<String, String, String>> toElfGroups(String rucksacksList) {
    List<Triple<String, String, String>> elfGroups = new ArrayList<>();
    List<String> elfs = rucksacksList.lines().toList();

    for (int i = 0; i < elfs.size(); i += 3) {
      elfGroups.add(Triple.of(elfs.get(i), elfs.get(i + 1), elfs.get(i + 2)));
    }

    return elfGroups;
  }

  private static Character findMisplacedBadge(Triple<String, String, String> elfGroup) {
    return elfGroup
        .getLeft()
        .chars()
        .mapToObj(itemCodePoint -> String.valueOf((char) itemCodePoint))
        .filter(elfGroup.getRight()::contains)
        .filter(elfGroup.getMiddle()::contains)
        .map(itemString -> itemString.charAt(0))
        .findFirst()
        .orElseThrow();
  }
}
