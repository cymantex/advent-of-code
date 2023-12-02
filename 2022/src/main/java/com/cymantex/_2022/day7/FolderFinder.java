package com.cymantex._2022.day7;

import com.cymantex._2022.Logger;

public class FolderFinder {

  public static int findSmallestFolderToDelete(String commands, int neededSpace,
      int availableSpace) {
    FileSystem fileSystem = createFileSystem(commands);

    Logger.debug(() -> System.out.println(fileSystem));

    int usedSpace = fileSystem.getRootFolder().getSize();
    int unusedSpace = availableSpace - usedSpace;
    int minSpaceToRelease = neededSpace - unusedSpace;

    return fileSystem
        .getFolders()
        .stream()
        .filter(folder -> folder.getSize() >= minSpaceToRelease)
        .mapToInt(Folder::getSize)
        .sorted()
        .findFirst()
        .orElseThrow();
  }

  public static int sumSizeOfFoldersBelowMaxSize(String commands, int maxSize) {
    return createFileSystem(commands)
        .getFolders()
        .stream()
        .filter(folder -> folder.getSize() <= maxSize)
        .mapToInt(Folder::getSize)
        .sum();
  }

  private static FileSystem createFileSystem(String commands) {
    return new FileSystemCrawler()
        .executeCommands(commands)
        .getFileSystem();
  }
}
