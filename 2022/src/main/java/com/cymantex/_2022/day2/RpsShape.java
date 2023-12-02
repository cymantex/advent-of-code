package com.cymantex._2022.day2;

public enum RpsShape {
  ROCK(1),
  PAPER(2),
  SCISSORS(3);

  private static final int SCORE_FOR_WINNING = 6;
  private static final int SCORE_FOR_DRAWING = 3;
  private static final int SCORE_FOR_LOSING = 0;

  private final int score;

  RpsShape(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public static RpsShape fromLetter(String letter) {
    return switch (letter) {
      case "A", "X" -> ROCK;
      case "B", "Y" -> PAPER;
      case "C", "Z" -> SCISSORS;
      default -> throw new IllegalArgumentException("Invalid letter: " + letter);
    };
  }

  public int getSpecialStrategyScoreAgainst(RpsShape opponentShape) {
    return switch (this) {
      case ROCK -> getLosingScore(opponentShape);
      case PAPER -> getDrawingScore(opponentShape);
      case SCISSORS -> getWinningScore(opponentShape);
    };
  }

  public int getScoreAgainst(RpsShape opponentShape) {
    return switch (this) {
      case ROCK -> getRockScore(opponentShape) + getScore();
      case PAPER -> getPaperScore(opponentShape) + getScore();
      case SCISSORS -> getScissorsScore(opponentShape) + getScore();
    };
  }

  private int getLosingScore(RpsShape opponentShape) {
    return switch (opponentShape) {
      case ROCK -> SCISSORS.getScore() + SCORE_FOR_LOSING;
      case PAPER -> ROCK.getScore() + SCORE_FOR_LOSING;
      case SCISSORS -> PAPER.getScore() + SCORE_FOR_LOSING;
    };
  }

  private int getDrawingScore(RpsShape opponentShape) {
    return switch (opponentShape) {
      case ROCK -> ROCK.getScore() + SCORE_FOR_DRAWING;
      case PAPER -> PAPER.getScore() + SCORE_FOR_DRAWING;
      case SCISSORS -> SCISSORS.getScore() + SCORE_FOR_DRAWING;
    };
  }

  private int getWinningScore(RpsShape opponentShape) {
    return switch (opponentShape) {
      case ROCK -> PAPER.getScore() + SCORE_FOR_WINNING;
      case PAPER -> SCISSORS.getScore() + SCORE_FOR_WINNING;
      case SCISSORS -> ROCK.getScore() + SCORE_FOR_WINNING;
    };
  }

  private int getRockScore(RpsShape opponentShape) {
    return switch (opponentShape) {
      case ROCK -> SCORE_FOR_DRAWING;
      case PAPER -> SCORE_FOR_LOSING;
      case SCISSORS -> SCORE_FOR_WINNING;
    };
  }

  private int getPaperScore(RpsShape opponentShape) {
    return switch (opponentShape) {
      case ROCK -> SCORE_FOR_WINNING;
      case PAPER -> SCORE_FOR_DRAWING;
      case SCISSORS -> SCORE_FOR_LOSING;
    };
  }

  private int getScissorsScore(RpsShape opponentShape) {
    return switch (opponentShape) {
      case ROCK -> SCORE_FOR_LOSING;
      case PAPER -> SCORE_FOR_WINNING;
      case SCISSORS -> SCORE_FOR_DRAWING;
    };
  }
}
