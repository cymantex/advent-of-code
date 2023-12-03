import { parseEngineSchematic } from "./engineSchematic.ts";
import {
  calcPartNumberSum,
  findAdjacentNumberPositions,
  findAdjacentNumbers,
  calcGearRatioSum,
  hasAdjacentSymbol,
} from "./day3.ts";

const schematic = `
1...
.23*
...4
  `;

const testSchematic = `
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
`;

const engineSchematic = parseEngineSchematic(schematic.trim().split("\n"));
const engineTestSchematic = parseEngineSchematic(testSchematic.trim().split("\n"));

test("findAdjacentNumberPositions", () => {
  expect(
    findAdjacentNumberPositions(engineSchematic.numbers[0], engineSchematic.symbolMap)
  ).toEqual([
    { x: 1, y: 0 },
    { x: 1, y: 1 },
    { x: 0, y: 1 },
  ]);
  expect(
    findAdjacentNumberPositions(engineSchematic.numbers[1], engineSchematic.symbolMap)
  ).toEqual([
    { x: 0, y: 0 },
    { x: 1, y: 0 },
    { x: 2, y: 0 },
    { x: 3, y: 0 },
    { x: 3, y: 1 },
    { x: 3, y: 2 },
    { x: 2, y: 2 },
    { x: 1, y: 2 },
    { x: 0, y: 2 },
    { x: 0, y: 1 },
  ]);
  expect(
    findAdjacentNumberPositions(engineSchematic.numbers[2], engineSchematic.symbolMap)
  ).toEqual([
    { x: 2, y: 1 },
    { x: 3, y: 1 },
    { x: 2, y: 2 },
  ]);
});

test("findAdjacentSymbols", () => {
  expect(hasAdjacentSymbol(engineSchematic.numbers[0], engineSchematic.symbolMap)).toBeFalsy();
  expect(hasAdjacentSymbol(engineSchematic.numbers[1], engineSchematic.symbolMap)).toBeTruthy();
  expect(hasAdjacentSymbol(engineSchematic.numbers[2], engineSchematic.symbolMap)).toBeTruthy();
});

test("calcPartNumberSum", () => {
  expect(calcPartNumberSum(schematic.trim().split("\n"))).toEqual(27);
  expect(calcPartNumberSum(testSchematic.trim().split("\n"))).toEqual(4361);
});

test("findAdjacentNumbers", () => {
  expect(
    findAdjacentNumbers(
      {
        x: 2,
        y: 1,
      },
      engineTestSchematic
    ).map((number) => number.number)
  ).toEqual([467, 35]);
});

test("findGears", () => {
  expect(calcGearRatioSum(testSchematic.trim().split("\n"))).toBe(467835);
});
