package com.cymantex._2022.day6;

import java.util.Arrays;

public class TuningTrouble {
  public static int findStartOfMessageMarker(String dataStream) {
    return findMarker(dataStream, 14);
  }

  public static int findStartOfPacketMarker(String dataStream) {
    return findMarker(dataStream, 4);
  }

  private static int findMarker(String dataStream, int markerSize) {
    DataStreamIterator dataStreamIterator = new DataStreamIterator(dataStream, markerSize);

    while (dataStreamIterator.hasNext()) {
      String dataChunk = dataStreamIterator.next();

      if (isMarker(dataChunk, markerSize)) {
        return (dataStreamIterator.getCurrentChunkIndex() + markerSize) - 1;
      }
    }

    throw new IllegalStateException("No marker found");
  }

  private static boolean isMarker(String chunk, int markerSize) {
    return Arrays.stream(chunk.split("")).distinct().count() == markerSize;
  }
}
