export interface Position {
  x: number;
  y: number;
}

export const createPositions = (y: number, xStart: number, xEnd: number): Position[] => {
  const positions: Position[] = [];

  for (let x = xStart; x <= xEnd; x++) {
    positions.push({ x, y });
  }

  return positions;
};
