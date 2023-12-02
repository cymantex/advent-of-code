export const CUBE_COLORS = ["blue", "red", "green"] as const;
type CubeColor = (typeof CUBE_COLORS)[number];
export type CubeSet = Record<CubeColor, number>;

export const EMPTY_CUBE_SET: CubeSet = {
  blue: 0,
  red: 0,
  green: 0,
} as const;

export const AVAILABLE_CUBES: CubeSet = {
  blue: 14,
  red: 12,
  green: 13,
} as const;

export interface Game {
  id: number;
  sets: CubeSet[];
}
