import { createPositions, Position } from "./position.ts";
import { SymbolMap } from "./SymbolMap.ts";

export interface EngineSchematicNumber {
  positions: Position[];
  number: number;
}

export interface EngineSchematic {
  symbolMap: SymbolMap;
  numbers: EngineSchematicNumber[];
}

export const parseEngineSchematic = (engineSchematicLines: string[]): EngineSchematic => {
  const symbolMap = parseSymbolMap(engineSchematicLines);

  return {
    symbolMap: symbolMap,
    numbers: parseNumbers(engineSchematicLines),
  };
};

export const parseSymbolMap = (engineSchematicLines: string[]): SymbolMap => {
  const symbolMap = new SymbolMap();

  for (let y = 0; y < engineSchematicLines.length; y++) {
    const line = engineSchematicLines[y].trim();

    for (let x = 0; x < line.length; x++) {
      const symbol = line.charAt(x);
      symbolMap.set({ x, y }, symbol);
    }
  }

  return symbolMap;
};

export const parseNumbers = (engineSchematicLines: string[]): EngineSchematicNumber[] => {
  const engineSchematicNumbers: EngineSchematicNumber[] = [];

  for (let y = 0; y < engineSchematicLines.length; y++) {
    const engineSchematicLine = engineSchematicLines[y].trim();
    engineSchematicNumbers.push(...parseNumbersForLine(engineSchematicLine, y));
  }

  return engineSchematicNumbers;
};

const parseNumbersForLine = (engineSchematicLine: string, y: number): EngineSchematicNumber[] =>
  Array.from(engineSchematicLine.matchAll(new RegExp("([0-9])+", "g"))).map((match) => ({
    number: parseInt(match[0], 10),
    positions: createPositions(y, match.index!, match.index! + (match[0].length - 1)),
  }));
