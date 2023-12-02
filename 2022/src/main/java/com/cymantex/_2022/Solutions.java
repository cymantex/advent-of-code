package com.cymantex._2022;

import static com.cymantex._2022.FileReader.readFile;
import static java.lang.System.out;

import com.cymantex._2022.day1.CalorieCounting;
import com.cymantex._2022.day10.CathodeAnalyzer;
import com.cymantex._2022.day2.RpsTournament;
import com.cymantex._2022.day3.MisplacedBadges;
import com.cymantex._2022.day3.RucksackReorganization;
import com.cymantex._2022.day4.CampCleanup;
import com.cymantex._2022.day5.CraneOperator;
import com.cymantex._2022.day5.CrateMover9000;
import com.cymantex._2022.day5.CrateMover9001;
import com.cymantex._2022.day6.TuningTrouble;
import com.cymantex._2022.day7.FolderFinder;
import com.cymantex._2022.day8.VisibleTrees;
import com.cymantex._2022.day9.RopeBridge;
import java.util.Set;

public class Solutions {

  public static void main(String[] a) {
    Logger.setLoglevel(Logger.INFO);
    for(int day = 0; day < 10; day++) {
      Solutions.printSolution(day);
    }
  }

  @SuppressWarnings("SameParameterValue")
  private static void printSolution(int day) {
    String sampleInput = readFile(String.format("day%d-sample.txt", day)).orElse(null);
    String input = readFile(String.format("day%d.txt", day)).orElseThrow();
    out.printf("Day %d - Solution:%n", day);

    switch (day) {
      case 1 -> day1_calorieCounting(input);
      case 2 -> day2_rpsTournament(input);
      case 3 -> day3_ruckSacks(input);
      case 4 -> day4_assignmentPairs(input);
      case 5 -> day5_crates(input);
      case 6 -> day6_turningTrouble(input);
      case 7 -> day7_folderFinder(input);
      case 8 -> day8_countTrees(input);
      case 9 -> day9_ropeBridge(input);
      case 10 -> day10_cathodeAnalyzer(input, sampleInput);
    }
  }

  private static void day10_cathodeAnalyzer(String sampleInstructions, String instructions) {
    Set<Integer> cycles = Set.of(20, 60, 100, 140, 180, 220);

    out.println(CathodeAnalyzer.sumSignalStrengths(sampleInstructions, cycles));
    out.println(CathodeAnalyzer.sumSignalStrengths(instructions, cycles));

    CathodeAnalyzer.drawCrt(sampleInstructions);
    out.println();
    CathodeAnalyzer.drawCrt(instructions);
  }

  private static void day9_ropeBridge(String movementInstructions) {
    out.println(new RopeBridge(movementInstructions, 3)
        .traverse()
        .getUniqueTailPositionsSize());
  }

  private static void day8_countTrees(String treeMap) {
    out.println(VisibleTrees.countVisibleTrees(treeMap));
    out.println(VisibleTrees.getMaxScenicScore(treeMap));
  }

  private static void day7_folderFinder(String commands) {
    out.println(FolderFinder.sumSizeOfFoldersBelowMaxSize(commands, 100_000));
    out.println(FolderFinder.findSmallestFolderToDelete(commands, 30_000_000, 70_000_000));
  }

  private static void day6_turningTrouble(String instructions) {
    out.println(TuningTrouble.findStartOfPacketMarker(instructions));
    out.println(TuningTrouble.findStartOfMessageMarker(instructions));
  }

  private static void day5_crates(String crateMovingInstructions) {
    CraneOperator craneOperator = new CraneOperator(crateMovingInstructions);

    out.println(craneOperator.operate(new CrateMover9000(crateMovingInstructions)));
    out.println(craneOperator.operate(new CrateMover9001(crateMovingInstructions)));
  }

  private static void day4_assignmentPairs(String assignmentPairs) {
    out.println(CampCleanup.countFullyContainedAssignmentPairs(assignmentPairs));
    out.println(CampCleanup.countAssignmentPairsWithAnyOverlap(assignmentPairs));
  }

  private static void day3_ruckSacks(String rucksacks) {
    out.println(RucksackReorganization.sumUnorganizedItemPriorities(rucksacks));
    out.println(MisplacedBadges.sumMisplacedBadgePriorities(rucksacks));
  }

  private static void day2_rpsTournament(String strategyGuide) {
    out.println(RpsTournament.getTotalScore(strategyGuide));
    out.println(RpsTournament.getTotalScoreFollowingSpecialStrategy(strategyGuide));
  }

  private static void day1_calorieCounting(String calorieCounts) {
    out.println(CalorieCounting.highestTotalCalories(calorieCounts));
    out.println(CalorieCounting.top3HighestTotalCalories(calorieCounts));
  }
}
