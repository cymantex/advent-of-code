import {
  calcMarginOfError,
  countWaysToBeatDistance,
  parseNumber,
  parseNumbers,
  parseRaces,
} from "./day6.ts";

const exampleInput = `
Time:      7  15   30
Distance:  9  40  200
`
  .trim()
  .split("\n");

test("parseRaces", () => {
  expect(parseRaces(exampleInput, parseNumbers)).toEqual([
    { time: 7, distance: 9 },
    { time: 15, distance: 40 },
    { time: 30, distance: 200 },
  ]);
});

test("countWaysToBeatDistance", () => {
  const race1 = parseRaces(exampleInput, parseNumbers)[0];

  expect(countWaysToBeatDistance(race1)).toBe(4);
});

test("calcMarginOfError", () => {
  expect(calcMarginOfError(exampleInput, parseNumbers)).toBe(288);
  expect(calcMarginOfError(exampleInput, parseNumber)).toBe(71503);
});
