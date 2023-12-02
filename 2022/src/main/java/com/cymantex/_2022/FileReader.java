package com.cymantex._2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class FileReader {
  public static Optional<String> readFile(String fileName) {
    ClassLoader classLoader = FileReader.class.getClassLoader();

    if (classLoader.getResource(fileName) == null) {
      return Optional.empty();
    }

    try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
      return Optional.of(readFromInputStream(inputStream));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String readFromInputStream(InputStream inputStream) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        stringBuilder.append(line).append("\n");
      }
    }

    return stringBuilder.toString();
  }
}
