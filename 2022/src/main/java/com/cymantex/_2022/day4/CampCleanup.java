package com.cymantex._2022.day4;

import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;

public class CampCleanup {

  public static long countAssignmentPairsWithAnyOverlap(String assignmentPairs) {
    return toAssignmentPairStream(assignmentPairs)
        .filter(
            assignmentGroup -> assignmentGroup.getLeft().hasOverlapWith(assignmentGroup.getRight())
                || assignmentGroup.getRight().hasOverlapWith(assignmentGroup.getLeft()))
        .count();
  }

  public static long countFullyContainedAssignmentPairs(String assignmentPairs) {
    return toAssignmentPairStream(assignmentPairs)
        .filter(assignmentPair ->
            assignmentPair.getLeft().contains(assignmentPair.getRight()) ||
                assignmentPair.getRight().contains(assignmentPair.getLeft()))
        .count();
  }

  private static Stream<Pair<Assignment, Assignment>> toAssignmentPairStream(
      String assignmentPairs) {
    return assignmentPairs
        .lines()
        .map(line -> Arrays.stream(line.split(","))
            .map(Assignment::new)
            .toList())
        .map(assignmentGroup -> Pair.of(assignmentGroup.get(0), assignmentGroup.get(1)));
  }

}
