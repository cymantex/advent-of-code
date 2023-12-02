package com.cymantex._2022.day7;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystemCrawler {

  public static final String $ = "$";

  private final FileSystem fileSystem;
  private Deque<String> remainingCommands;

  public FileSystemCrawler() {
    fileSystem = new FileSystem();
  }

  public FileSystemCrawler executeCommands(String commands) {
    remainingCommands = commands.lines().collect(Collectors.toCollection(ArrayDeque::new));

    while (!remainingCommands.isEmpty()) {
      execute(remainingCommands.pop());
    }

    return this;
  }

  public FileSystem getFileSystem() {
    return fileSystem;
  }

  /**
   * Example input: $ cd /
   */
  private void execute(String rawCommand) {
    String[] commandTokens = rawCommand.split(" ");
    String command = commandTokens[1];

    switch (command) {
      case "ls" -> fileSystem.ls(toLsCommandResults(readCommandResult()));
      case "cd" -> fileSystem.cd(commandTokens[2]);
    }
  }

  private List<String> readCommandResult() {
    List<String> result = remainingCommands
        .stream()
        .takeWhile(commandLine -> !commandLine.startsWith($))
        .toList();

    remainingCommands = remainingCommands
        .stream()
        .dropWhile(commandLine -> !commandLine.startsWith($))
        .collect(Collectors.toCollection(ArrayDeque::new));

    return result;
  }

  private static List<LsCommandResult> toLsCommandResults(Collection<String> lsCommandResults) {
    return lsCommandResults
        .stream()
        .map(lsCommandResult -> lsCommandResult.split(" "))
        .map(lsCommandResult -> new LsCommandResult(lsCommandResult[0], lsCommandResult[1]))
        .toList();
  }
}
