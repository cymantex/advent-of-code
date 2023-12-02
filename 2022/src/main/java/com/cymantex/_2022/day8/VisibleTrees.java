package com.cymantex._2022.day8;

public class VisibleTrees {

  public static int countVisibleTrees(String treeMap) {
    return MapOfTrees
        .from(treeMap)
        .findVisibleTrees()
        .size();
  }

  public static int getMaxScenicScore(String treeMap) {
    return MapOfTrees
        .from(treeMap)
        .getMaxScenicScore();
  }
}
