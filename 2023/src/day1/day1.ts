import {
  DigitMatch,
  isSpelledDigit,
  MatchDigits,
  MatchDigitsArgs,
  SpelledDigit,
  spelledDigitToNumber,
} from "./typesAndConstants.ts";

// noinspection JSUnusedGlobalSymbols
export const printSolution = (calibrationLines: string[]) => {
  console.log(calcCalibrationValueSum(calibrationLines, matchDigits));
  console.log(calcCalibrationValueSum(calibrationLines, matchDigitsAndSpelledDigits));
};

export const calcCalibrationValueSum = (
  calibrationLines: string[],
  matchCalibrationLineDigits: MatchDigits
) => {
  return calibrationLines
    .map((calibrationLine) => toDigits(calibrationLine, matchCalibrationLineDigits))
    .map(toCalibrationValue)
    .reduce((values, value) => values + value, 0);
};

export const toCalibrationValue = (calibrationDigits: string): number =>
  parseInt(
    calibrationDigits.charAt(0) + calibrationDigits.charAt(calibrationDigits.length - 1),
    10
  );

export const toDigits = (str: string, matchDigits: MatchDigits) =>
  Object.entries(spelledDigitToNumber)
    .flatMap(([spelledDigit, digit]) =>
      matchDigits({
        calibrationLine: str,
        spelledDigit: spelledDigit as SpelledDigit,
        digit,
      })
    )
    .sort((digitMatch1, digitMatch2) => digitMatch1.index - digitMatch2.index)
    .map((digitMatch) => digitMatch.digit)
    .join("");

export const matchDigitsAndSpelledDigits = ({
  calibrationLine,
  spelledDigit,
  digit,
}: MatchDigitsArgs): DigitMatch[] => [
  ...matchDigit(calibrationLine, digit.toString()),
  ...matchDigit(calibrationLine, spelledDigit),
];

export const matchDigits = ({ calibrationLine, digit }: MatchDigitsArgs): DigitMatch[] =>
  matchDigit(calibrationLine, digit.toString());

export const matchDigit = (str: string, digit: string): DigitMatch[] =>
  Array.from(str.matchAll(new RegExp(digit, "g")))
    .filter((match) => match.length > 0)
    .map((match) => ({
      index: match.index!,
      digit: isSpelledDigit(match[0]) ? spelledDigitToNumber[match[0]] : parseInt(match[0], 10),
    }));
