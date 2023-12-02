export const SPELLED_DIGIT_TO_NUMBER = {
  zero: 0,
  one: 1,
  two: 2,
  three: 3,
  four: 4,
  five: 5,
  six: 6,
  seven: 7,
  eight: 8,
  nine: 9,
} as const;

export type SpelledDigit = keyof typeof SPELLED_DIGIT_TO_NUMBER;

export const isSpelledDigit = (str: string): str is SpelledDigit =>
  Object.keys(SPELLED_DIGIT_TO_NUMBER).includes(str);

export interface DigitMatch {
  index: number;
  digit: number;
}

export interface MatchDigitsArgs {
  calibrationLine: string;
  spelledDigit: SpelledDigit;
  digit: number;
}

export type MatchDigits = (args: MatchDigitsArgs) => DigitMatch[];
