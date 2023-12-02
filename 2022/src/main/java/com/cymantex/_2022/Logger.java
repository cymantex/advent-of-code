package com.cymantex._2022;

import java.util.function.Consumer;

public class Logger {
  public static final String INFO = "INFO";
  public static final String DEBUG = "DEBUG";

  private static String loglevel;

  public static void setLoglevel(String loglevel) {
    Logger.loglevel = loglevel;
  }

  public static void info(Runnable runnable) {
    if (INFO.equals(loglevel)) {
      runnable.run();
    }
  }

  public static void debug(Runnable runnable) {
    if (DEBUG.equals(loglevel)) {
      runnable.run();
    }
  }

  public static void debug(Object object, Consumer<Object> consumer) {
    if (DEBUG.equals(loglevel)) {
      consumer.accept(object);
    }
  }
}
