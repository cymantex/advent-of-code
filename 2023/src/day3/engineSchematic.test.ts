import { parseNumbers, parseSymbolMap } from "./engineSchematic.ts";

test("parseSymbolMap", () => {
  expect(parseSymbolMap(["46", ".."]).entries()).toEqual([
    [{ x: 0, y: 0 }, "4"],
    [{ x: 1, y: 0 }, "6"],
    [{ x: 0, y: 1 }, "."],
    [{ x: 1, y: 1 }, "."],
  ]);
});

test("parseNumbers", () => {
  expect(parseNumbers(["467..114..", ".4.", "5"])).toEqual([
    {
      number: 467,
      positions: [
        { x: 0, y: 0 },
        { x: 1, y: 0 },
        { x: 2, y: 0 },
      ],
    },
    {
      number: 114,
      positions: [
        { x: 5, y: 0 },
        { x: 6, y: 0 },
        { x: 7, y: 0 },
      ],
    },
    {
      number: 4,
      positions: [{ x: 1, y: 1 }],
    },
    {
      number: 5,
      positions: [{ x: 0, y: 2 }],
    },
  ]);
});
