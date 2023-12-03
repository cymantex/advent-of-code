import { EngineSchematic, EngineSchematicNumber, parseEngineSchematic } from "./engineSchematic.ts";
import { createPositions, Position } from "./position.ts";
import { SymbolMap } from "./SymbolMap.ts";
import { uniqBy } from "lodash";

// noinspection JSUnusedGlobalSymbols
export const printSolution = (engineSchemeticLines: string[]) => {
  console.log(calcPartNumberSum(engineSchemeticLines));
  console.log(calcGearRatioSum(engineSchemeticLines));
};

export const calcPartNumberSum = (engineSchematicLines: string[]) => {
  const engineSchematic = parseEngineSchematic(engineSchematicLines);

  return engineSchematic.numbers
    .filter((schematicNumber) => hasAdjacentSymbol(schematicNumber, engineSchematic.symbolMap))
    .map((schematicNumber) => schematicNumber.number)
    .reduce((sum, partNumber) => sum + partNumber, 0);
};

export const calcGearRatioSum = (engineSchematicLines: string[]) => {
  const engineSchematic = parseEngineSchematic(engineSchematicLines);

  return findStarSymbols(engineSchematic.symbolMap)
    .map((starPosition) => findAdjacentNumbers(starPosition, engineSchematic))
    .filter((numbers) => numbers.length === 2)
    .map((numbers) => numbers[0].number * numbers[1].number)
    .reduce((sum, gearRatio) => sum + gearRatio, 0);
};

export const findAdjacentNumbers = (
  position: Position,
  engineSchematic: EngineSchematic
): EngineSchematicNumber[] => {
  const symbolMap = engineSchematic.symbolMap;
  const adjacentPositions = findAdjacentPositions({
    start: position,
    end: position,
    symbolMap: symbolMap,
  });
  const adjacentNumbers = adjacentPositions
    .filter((position) => symbolMap.has(position))
    .filter((position) => symbolMap.get(position).match(/[0-9]/) !== null)
    .flatMap((numberPosition) =>
      engineSchematic.numbers.filter((number) =>
        number.positions.map((pos) => JSON.stringify(pos)).includes(JSON.stringify(numberPosition))
      )
    );

  return uniqBy(adjacentNumbers, (number) => JSON.stringify(number.positions));
};

export const hasAdjacentSymbol = (
  engineSchematicNumber: EngineSchematicNumber,
  symbolMap: SymbolMap
): boolean =>
  findAdjacentPositions({
    start: engineSchematicNumber.positions[0],
    end: engineSchematicNumber.positions[engineSchematicNumber.positions.length - 1],
    symbolMap: symbolMap,
  })
    .map((position) => symbolMap.get(position))
    .some((symbol) => symbol !== "." && symbol.match(/[0-9]/) === null);

export const findAdjacentNumberPositions = (
  engineSchematicNumber: EngineSchematicNumber,
  symbolMap: SymbolMap
): Position[] =>
  findAdjacentPositions({
    start: engineSchematicNumber.positions[0],
    end: engineSchematicNumber.positions[engineSchematicNumber.positions.length - 1],
    symbolMap: symbolMap,
  });

const findAdjacentPositions = ({
  start,
  end,
  symbolMap,
}: {
  start: Position;
  end: Position;
  symbolMap: SymbolMap;
}): Position[] => {
  const y = start.y;
  const northPositions = createPositions(y - 1, start.x - 1, end.x + 1);
  const right = { x: end.x + 1, y };
  const southPositions = createPositions(y + 1, start.x - 1, end.x + 1).reverse();
  const left = { x: start.x - 1, y };

  return [...northPositions, right, ...southPositions, left].filter((position) =>
    symbolMap.has(position)
  );
};

const findStarSymbols = (symbolMap: SymbolMap) =>
  symbolMap
    .entries()
    .filter(([, symbol]) => symbol === "*")
    .map(([position]) => position);
