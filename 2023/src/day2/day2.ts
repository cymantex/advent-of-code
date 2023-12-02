import {
  availableCubes,
  cubeColors,
  CubeSet,
  emptyCubeSet,
  Game,
  isCubeColor,
} from "./typesAndConstants.ts";

// noinspection JSUnusedGlobalSymbols
export const printSolution = (games: string[]) => {
  console.log(calcIdSumOfPossibleGames(games));
  console.log(calcPowerSumOfRequiredCubes(games));
};

export const calcPowerSumOfRequiredCubes = (games: string[]): number => {
  return games
    .map(parseGame)
    .map((game) => countRequiredCubes(game.sets))
    .map((cubeSet) => cubeSet.red * cubeSet.blue * cubeSet.green)
    .reduce((sum, power) => sum + power, 0);
};

export const calcIdSumOfPossibleGames = (games: string[]): number => {
  return games
    .map(parseGame)
    .filter((game) => gameIsPossible(game, availableCubes))
    .map((game) => game.id)
    .reduce((ids, id) => ids + id, 0);
};

export const gameIsPossible = (game: Game, availableCubes: CubeSet): boolean => {
  return game.sets.every((set) => cubeColors.every((color) => set[color] <= availableCubes[color]));
};

export const parseGame = (gameLine: string): Game => {
  const gameTokens = gameLine.split(":");
  const id = parseInt(gameTokens[0].split(" ")[1], 10);
  const sets: CubeSet[] = gameTokens[1].split(";").map((set) => ({
    ...emptyCubeSet,
    ...parseCubeSet(set),
  }));
  return { id, sets };
};

const parseCubeSet = (gameSetString: string): Partial<CubeSet> =>
  gameSetString
    .split(",")
    .map(parseRevealedCubes)
    .reduce((cubeSets, cubeSet) => ({ ...cubeSets, ...cubeSet }), {});

const parseRevealedCubes = (cubeReveal: string): Partial<CubeSet> => {
  const [count, color] = cubeReveal.trim().split(" ");

  if (!isCubeColor(color)) {
    throw new Error(`Invalid color: ${color}`);
  }

  return { [color]: parseInt(count, 10) };
};

export const countRequiredCubes = (sets: CubeSet[]): CubeSet => {
  const requiredCubes: CubeSet = { ...emptyCubeSet };

  for (const set of sets) {
    for (const color of cubeColors) {
      if (requiredCubes[color] < set[color]) {
        requiredCubes[color] = set[color];
      }
    }
  }

  return requiredCubes;
};
