export interface Almanac {
  seedNumbers: number[];
  seedRanges: SeedRange[];
  maps: AlmanacMap[];
}

export interface SeedRange {
  number: number;
  range: number;
}

export interface AlmanacMap {
  name: string;
  mappings: AlmanacMapping[];
}

export interface AlmanacMapping {
  sourceRangeStart: number;
  destinationRangeStart: number;
  rangeLength: number;
}
