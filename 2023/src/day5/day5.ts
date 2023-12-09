import { chunk, parseInt, tail } from "lodash";
import { Almanac, AlmanacMap, SeedRange } from "./typesAndConstants.ts";

export const printSolution = (almanac: string) => {
  console.log(findShortestLocationBySeedNumber(almanac));
  console.log(findShortestLocationBySeedRange(almanac));
};

export const findShortestLocationBySeedNumber = (almanacInput: string): number => {
  const almanac = parseAlmanac(almanacInput);
  const locations = almanac.seedNumbers.map((seed) => findFinalMappingBySeedNumber(seed, almanac));
  return Math.min(...locations);
};

export const findShortestLocationBySeedRange = (almanacInput: string): number => {
  const almanac = parseAlmanac(almanacInput);
  const locations = almanac.seedRanges.map((seed, i) =>
    slow_findShortestLocationInSeedRange(i, almanac, seed)
  );
  return Math.min(...locations);
};

export const findFinalMappingBySeedNumber = (seed: number, almanac: Almanac): number =>
  almanac.maps.reduce((seed, almanacMap) => findMappingBySeedNumber(seed, almanacMap), seed);

export const findMappingBySeedNumber = (seed: number, almanacMap: AlmanacMap): number => {
  const seedMapping = almanacMap.mappings.find(
    ({ sourceRangeStart, rangeLength }) =>
      seed >= sourceRangeStart && seed <= sourceRangeStart + rangeLength
  );

  return !seedMapping
    ? seed
    : seed + (seedMapping.destinationRangeStart - seedMapping.sourceRangeStart);
};

export const parseAlmanac = (almanac: string): Almanac => {
  const almanacTokens = almanac.split("\n\n");
  const seedNumbers = almanacTokens[0].split(": ")[1].split(" ").map(parseInt);
  return {
    seedNumbers,
    seedRanges: chunk(seedNumbers, 2).map(([number, range]) => ({ number, range })),
    maps: tail(almanacTokens).map(parseAlmanacMap),
  };
};

export const parseAlmanacMap = (almanacMap: string) => {
  const almanacMapTokens = almanacMap.split("\n");
  return {
    name: almanacMapTokens[0].split(":")[0].split(" ")[0],
    mappings: tail(almanacMapTokens).map((mapping) => {
      const mappingTokens = mapping.split(" ");
      const destinationRangeStart = parseInt(mappingTokens[0]);
      const sourceRangeStart = parseInt(mappingTokens[1]);
      const rangeLength = parseInt(mappingTokens[2]);
      return { sourceRangeStart, destinationRangeStart, rangeLength };
    }),
  };
};

const slow_findShortestLocationInSeedRange = (
  seedIndex: number,
  almanac: Almanac,
  seed: SeedRange
) => {
  console.log(
    `Processing seed ${seedIndex + 1} of ${almanac.seedNumbers.length / 2}, with a range of ${
      seed.range
    }`
  );
  let shortestLocation = Infinity;

  for (let i = 0; i < seed.range; i++) {
    const location = findFinalMappingBySeedNumber(seed.number + i, almanac);

    if (i > 0 && i % 10000000 === 0) {
      console.log(`Processed ${i} seeds`);
    }

    if (location < shortestLocation) {
      shortestLocation = location;
    }
  }

  return shortestLocation;
};
