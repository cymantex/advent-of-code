package com.cymantex._2022.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalorieCounting {

  public static int highestTotalCalories(String calorieCountsInput) {
    return toCalorieSumsInDescendingOrder(calorieCountsInput).get(0);
  }

  public static int top3HighestTotalCalories(String calorieCountsInput) {
    List<Integer> calorieSums = toCalorieSumsInDescendingOrder(calorieCountsInput);
    return calorieSums.get(0) + calorieSums.get(1) + calorieSums.get(2);
  }

  private static List<Integer> toCalorieSumsInDescendingOrder(String calorieCountsInput) {
    List<Integer> calorieSums = new ArrayList<>();
    String[] calorieCountsList = calorieCountsInput.split("\n\n");

    for (String calorieCounts : calorieCountsList) {
      int calorieSum = calorieCounts
          .lines()
          .mapToInt(Integer::parseInt)
          .sum();

      calorieSums.add(calorieSum);
    }

    calorieSums.sort(Collections.reverseOrder());

    return calorieSums;
  }
}
