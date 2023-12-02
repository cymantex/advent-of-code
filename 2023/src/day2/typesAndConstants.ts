export const cubeColors = ["blue", "red", "green"] as const;
type CubeColor = (typeof cubeColors)[number];
export type CubeSet = Record<CubeColor, number>;
export const isCubeColor = (color: string): color is CubeColor =>
  cubeColors.includes(color as CubeColor);

export const emptyCubeSet: CubeSet = {
  blue: 0,
  red: 0,
  green: 0,
} as const;

export const availableCubes: CubeSet = {
  blue: 14,
  red: 12,
  green: 13,
} as const;

export interface Game {
  id: number;
  sets: CubeSet[];
}
