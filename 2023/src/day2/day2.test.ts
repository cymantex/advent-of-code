import { countRequiredCubes, isGamePossible, parseGame } from "./day2.ts";

test("parseGame", () => {
  expect(parseGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue")).toEqual({
    id: 1,
    sets: [
      { blue: 3, red: 4, green: 0 },
      { red: 1, green: 2, blue: 6 },
    ],
  });
});

test("isGamePossible", () => {
  const availableCubes = {
    blue: 12,
    red: 13,
    green: 14,
  };

  expect(isGamePossible({ id: 1, sets: [] }, availableCubes)).toBe(true);
  expect(
    isGamePossible(
      {
        id: 1,
        sets: [{ blue: 0, red: 14, green: 0 }],
      },
      availableCubes
    )
  ).toBe(false);
  expect(
    isGamePossible(
      {
        id: 1,
        sets: [{ blue: 12, red: 13, green: 14 }],
      },
      availableCubes
    )
  ).toBe(true);
});

test("countRequiredCubes", () => {
  expect(
    countRequiredCubes([
      { blue: 3, red: 4, green: 0 },
      {
        red: 1,
        green: 2,
        blue: 6,
      },
    ])
  ).toEqual({
    blue: 6,
    red: 4,
    green: 2,
  });
});
