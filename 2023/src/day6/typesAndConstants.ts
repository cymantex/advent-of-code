export interface Race {
  time: number;
  distance: number;
}

export type ParseNumbersFunc = (raceLine: string) => number[];
