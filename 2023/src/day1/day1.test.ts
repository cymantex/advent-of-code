import {
  matchDigit,
  matchDigits,
  matchDigitsAndSpelledDigits,
  toCalibrationValue,
  toDigits,
} from "./day1.ts";

test("toCalibrationValue", () => {
  expect(toCalibrationValue("348")).toBe(38);
});

test("matchDigit", () => {
  expect(matchDigit("two1two", "1")).toEqual([{ index: 3, digit: 1 }]);
  expect(matchDigit("two1two", "two")).toEqual([
    { index: 0, digit: 2 },
    { index: 4, digit: 2 },
  ]);
});

describe("toDigits", () => {
  test("matchDigits", () => {
    expect(toDigits("1abc2", matchDigits)).toBe("12");
  });

  test("matchDigitsAndSpelledDigits", () => {
    expect(toDigits("two1nine", matchDigitsAndSpelledDigits)).toBe("219");
    expect(toDigits("zoneight234", matchDigitsAndSpelledDigits)).toBe("18234");
    expect(toDigits("7pqrstsixteen", matchDigitsAndSpelledDigits)).toBe("76");
  });
});
