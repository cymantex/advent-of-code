import { ParseNumbersFunc, Race } from "./typesAndConstants.ts";
import { parseInt, zip } from "lodash";

export const printSolution = (races: string[]) => {
  console.log(calcMarginOfError(races, parseNumbers));
  console.log(calcMarginOfError(races, parseNumber));
};

export const calcMarginOfError = (races: string[], onParseNumbers: ParseNumbersFunc): number =>
  parseRaces(races, onParseNumbers)
    .map(countWaysToBeatDistance)
    .reduce((race, ways) => race * ways);

export const countWaysToBeatDistance = (race: Race): number => {
  let numberOfWays = 0;

  for (let i = 0; i < race.time; i++) {
    const milliseconds = race.time - i;

    if (milliseconds * i > race.distance) {
      numberOfWays++;
    }
  }

  return numberOfWays;
};

export const parseRaces = (raceLines: string[], onParseNumbers: ParseNumbersFunc): Race[] =>
  zip(onParseNumbers(raceLines[0]), onParseNumbers(raceLines[1])).map(([time, distance]) => ({
    time: time!,
    distance: distance!,
  }));

export const parseNumbers = (raceLine: string) =>
  raceLine
    .split(": ")[1]
    .split(" ")
    .filter((milliseconds) => milliseconds.trim())
    .map(parseInt);

export const parseNumber = (raceLine: string) => [
  parseInt(raceLine.split(": ")[1].replaceAll(" ", "")),
];
