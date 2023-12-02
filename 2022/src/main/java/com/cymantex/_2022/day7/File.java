package com.cymantex._2022.day7;

public record File(String name, int size, Folder parent) {

  @Override
  public String toString() {
    return String.format("%s- %s (file, size=%s)", indent(), name, size);
  }

  private String indent() {
    return parent.getIndent() + " ".repeat(Folder.INDENT_SPACES);
  }
}
