package com.cymantex._2022.day2;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class RpsTournament {

  public static int getTotalScoreFollowingSpecialStrategy(String specialStrategyGuide) {
    return toRpsShapePairs(specialStrategyGuide)
        .stream()
        .mapToInt(pair -> pair.getRight().getSpecialStrategyScoreAgainst(pair.getLeft()))
        .sum();
  }

  public static int getTotalScore(String strategyGuide) {
    return toRpsShapePairs(strategyGuide)
        .stream()
        .mapToInt(pair -> pair.getRight().getScoreAgainst(pair.getLeft()))
        .sum();
  }

  private static List<Pair<RpsShape, RpsShape>> toRpsShapePairs(String strategyGuide) {
    return strategyGuide
        .lines()
        .map(round -> Arrays
            .stream(round.split(" "))
            .map(RpsShape::fromLetter)
            .toList())
        .map(rpsShapes -> Pair.of(rpsShapes.get(0), rpsShapes.get(1)))
        .toList();
  }
}
