package com.cymantex._2022.day7;

public record LsCommandResult(String sizeOrDir, String dirOrFileName) {
  public boolean isDir() {
    return sizeOrDir.equals("dir");
  }

  public boolean isFile() {
    return !isDir();
  }
}
