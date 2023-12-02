package com.cymantex._2022.day7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Folder {

  public static final int INDENT_SPACES = 2;

  private final List<Folder> folders = new ArrayList<>();
  private final List<File> files = new ArrayList<>();
  private final Folder parent;
  private final String name;

  public Folder(String name, Folder parent) {
    this.name = name;
    this.parent = parent;
  }

  public void addFolders(Collection<Folder> folders) {
    this.folders.addAll(folders);
  }

  public void addFiles(Collection<File> files) {
    this.files.addAll(files);
  }

  public Folder findOrThrow(String folderName) {
    return folders.stream()
        .filter(folder -> folderName.equals(folder.getName()))
        .findFirst()
        .orElseThrow();
  }

  public List<Folder> getFolders() {
    return folders;
  }

  public int getSize() {
    int filesSize = files
        .stream()
        .mapToInt(File::size)
        .sum();
    int subfolderSize = folders
        .stream()
        .mapToInt(Folder::getSize)
        .sum();

    return filesSize + subfolderSize;
  }

  public String getName() {
    return name;
  }

  public Folder getParent() {
    return parent;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(
        String.format("%s- %s (dir, size=%s)", getIndent(), name, getSize()));

    if (!folders.isEmpty()) {
      stringBuilder
          .append("\n")
          .append(folders.stream().map(Folder::toString).collect(Collectors.joining("\n")));
    }

    if (!files.isEmpty()) {
      stringBuilder
          .append("\n")
          .append(files.stream().map(File::toString).collect(Collectors.joining("\n")));
    }

    return stringBuilder.toString();
  }

  String getIndent() {
    return " ".repeat(countNumberOfParentFolders() * INDENT_SPACES);
  }

  private int countNumberOfParentFolders() {
    int numberOfParentFolders = 0;
    Folder folder = parent;

    while (folder != null) {
      numberOfParentFolders++;
      folder = folder.parent;
    }

    return numberOfParentFolders;
  }
}
