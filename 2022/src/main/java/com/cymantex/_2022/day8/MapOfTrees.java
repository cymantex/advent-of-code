package com.cymantex._2022.day8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapOfTrees {

  private final Map<Position, Integer> mapOfTrees;
  private final int maxWidth;
  private final int maxHeight;

  private MapOfTrees(Map<Position, Integer> mapOfTrees, int maxWidth, int maxHeight) {
    this.mapOfTrees = mapOfTrees;
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
  }

  public static MapOfTrees from(String treeMapInput) {
    List<String> rows = treeMapInput.lines().toList();
    Map<Position, Integer> mapOfTrees = new HashMap<>();

    for (int y = 0; y < rows.size(); y++) {
      List<String> column = Arrays.asList(rows.get(y).split(""));

      for (int x = 0; x < column.size(); x++) {
        mapOfTrees.put(new Position(x, y), Integer.parseInt(column.get(x)));
      }
    }

    return new MapOfTrees(mapOfTrees, rows.size(), rows.get(0).split("").length);
  }

  public int getMaxScenicScore() {
    return getTrees().stream()
        .mapToInt(this::getScenicScore)
        .max()
        .orElseThrow();
  }

  public List<Tree> findVisibleTrees() {
    return getTrees().stream()
        .filter(this::isVisible)
        .toList();
  }

  private int getScenicScore(Tree tree) {
    return getNumberOfVisibleTreesInDirection(tree, Direction.NORTH) *
        getNumberOfVisibleTreesInDirection(tree, Direction.SOUTH) *
        getNumberOfVisibleTreesInDirection(tree, Direction.EAST) *
        getNumberOfVisibleTreesInDirection(tree, Direction.WEST);
  }

  private int getNumberOfVisibleTreesInDirection(Tree tree, Direction direction) {
    Position position = tree.position();
    Position nextPosition = position.to(direction, maxWidth, maxHeight).orElse(null);
    int numberOfVisibleTrees = 0;

    while (nextPosition != null) {
      numberOfVisibleTrees++;

      if (mapOfTrees.get(nextPosition) >= tree.height()) {
        return numberOfVisibleTrees;
      }

      nextPosition = nextPosition.to(direction, maxWidth, maxHeight).orElse(null);
    }

    return numberOfVisibleTrees;
  }

  private boolean isVisible(Tree tree) {
    return treesInDirectionIsAllSmaller(tree, Direction.NORTH) ||
        treesInDirectionIsAllSmaller(tree, Direction.SOUTH) ||
        treesInDirectionIsAllSmaller(tree, Direction.EAST) ||
        treesInDirectionIsAllSmaller(tree, Direction.WEST);
  }

  private boolean treesInDirectionIsAllSmaller(Tree tree, Direction direction) {
    Position position = tree.position();
    Position nextPosition = position.to(direction, maxWidth, maxHeight).orElse(null);

    while (nextPosition != null) {
      if (mapOfTrees.get(nextPosition) >= tree.height()) {
        return false;
      }

      nextPosition = nextPosition.to(direction, maxWidth, maxHeight).orElse(null);
    }

    return true;
  }

  private List<Tree> getTrees() {
    return mapOfTrees.entrySet()
        .stream()
        .map(entry -> new Tree(entry.getKey(), entry.getValue()))
        .toList();
  }

  @Override
  public String toString() {
    return mapOfTrees
        .entrySet()
        .stream()
        .map(positionIntegerEntry -> String.format("(x: %s, y: %s, tree: %d)",
            positionIntegerEntry.getKey().x(),
            positionIntegerEntry.getKey().y(),
            positionIntegerEntry.getValue()))
        .collect(Collectors.joining("\n"));
  }
}
