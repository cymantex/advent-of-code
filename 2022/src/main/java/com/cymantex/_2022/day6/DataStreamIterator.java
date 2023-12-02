package com.cymantex._2022.day6;

import java.util.Iterator;

public class DataStreamIterator implements Iterator<String> {
  private final String dataStream;
  private final int chunkSize;
  private int currentChunkIndex;

  public DataStreamIterator(String dataStream, int chunkSize) {
    this.dataStream = dataStream;
    this.chunkSize = chunkSize;
    this.currentChunkIndex = 0;
  }

  public int getCurrentChunkIndex() {
    return currentChunkIndex;
  }

  @Override
  public boolean hasNext() {
    return currentChunkIndex + chunkSize < dataStream.length();
  }

  @Override
  public String next() {
    String nextChunk = dataStream.substring(currentChunkIndex, currentChunkIndex + chunkSize);
    currentChunkIndex++;
    return nextChunk;
  }
}
