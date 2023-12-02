import {
  AVAILABLE_CUBES,
  CUBE_COLORS,
  CubeSet,
  EMPTY_CUBE_SET,
  Game,
} from "./typesAndConstants.ts";

// noinspection JSUnusedGlobalSymbols
export const printSolution = (games: string[]) => {
  console.log(calcIdSumOfPossibleGames(games));
  console.log(calcPowerSumOfRequiredCubes(games));
};

export const calcPowerSumOfRequiredCubes = (games: string[]): number =>
  games
    .map(parseGame)
    .map((game) => countRequiredCubes(game.sets))
    .map((cubeSet) => cubeSet.red * cubeSet.blue * cubeSet.green)
    .reduce((sum, power) => sum + power, 0);

export const calcIdSumOfPossibleGames = (games: string[]): number =>
  games
    .map(parseGame)
    .filter((game) => isGamePossible(game, AVAILABLE_CUBES))
    .map((game) => game.id)
    .reduce((ids, id) => ids + id, 0);

export const isGamePossible = (game: Game, availableCubes: CubeSet): boolean =>
  game.sets.every((set) => CUBE_COLORS.every((color) => set[color] <= availableCubes[color]));

export const parseGame = (gameLine: string): Game => {
  const gameTokens = gameLine.split(":");
  const id = parseInt(gameTokens[0].split(" ")[1], 10);
  const sets: CubeSet[] = gameTokens[1].split(";").map((set) => ({
    ...EMPTY_CUBE_SET,
    ...parseCubeSet(set),
  }));
  return { id, sets };
};

const parseCubeSet = (gameSetString: string): Partial<CubeSet> =>
  gameSetString
    .split(",")
    .map(parseRevealedCubes)
    .reduce((cubeSets, cubeSet) => ({ ...cubeSets, ...cubeSet }), {});

const parseRevealedCubes = (revealedCubes: string): Partial<CubeSet> => {
  const [cubeCount, cubeColor] = revealedCubes.trim().split(" ");
  return { [cubeColor]: parseInt(cubeCount, 10) };
};

export const countRequiredCubes = (sets: CubeSet[]): CubeSet => {
  const requiredCubes: CubeSet = { ...EMPTY_CUBE_SET };

  for (const set of sets) {
    for (const color of CUBE_COLORS) {
      if (requiredCubes[color] < set[color]) {
        requiredCubes[color] = set[color];
      }
    }
  }

  return requiredCubes;
};
