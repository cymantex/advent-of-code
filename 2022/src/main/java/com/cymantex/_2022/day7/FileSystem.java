package com.cymantex._2022.day7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class FileSystem {
  private final Folder rootFolder = new Folder("/", null);
  private Folder currentFolder;

  public void cd(String path) {
    if (path.equals("/")) {
      currentFolder = rootFolder;
    } else if (path.equals("..")) {
      currentFolder = currentFolder.getParent();
    } else {
      currentFolder = currentFolder.findOrThrow(path);
    }
  }

  public void ls(Collection<LsCommandResult> lsCommandResults) {
    currentFolder.addFiles(toFiles(lsCommandResults));
    currentFolder.addFolders(toFolders(lsCommandResults));
  }

  public Folder getRootFolder() {
    return rootFolder;
  }

  public List<Folder> getFolders() {
    List<Folder> folders = new ArrayList<>();
    traverse(folders::add);
    return folders;
  }

  private void traverse(Consumer<Folder> folderConsumer) {
    folderConsumer.accept(rootFolder);
    rootFolder.getFolders().forEach(folder -> traverse(folder, folderConsumer));
  }

  private void traverse(Folder folder, Consumer<Folder> folderConsumer) {
    folderConsumer.accept(folder);
    folder.getFolders().forEach(childFolder -> traverse(childFolder, folderConsumer));
  }

  private List<Folder> toFolders(Collection<LsCommandResult> lsCommandResults) {
    return lsCommandResults.stream()
        .filter(LsCommandResult::isDir)
        .map(result -> new Folder(result.dirOrFileName(), currentFolder))
        .toList();
  }

  private List<File> toFiles(Collection<LsCommandResult> lsCommandResults) {
    return lsCommandResults.stream()
        .filter(LsCommandResult::isFile)
        .map(result -> new File(
            result.dirOrFileName(),
            Integer.parseInt(result.sizeOrDir()),
            currentFolder))
        .toList();
  }

  @Override
  public String toString() {
    return rootFolder.toString();
  }
}
