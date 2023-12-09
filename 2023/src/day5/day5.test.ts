import {
  findFinalMappingBySeedNumber,
  findMappingBySeedNumber,
  findShortestLocationBySeedNumber,
  findShortestLocationBySeedRange,
  parseAlmanac,
  parseAlmanacMap,
} from "./day5.ts";

const exampleInput = `
seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4
`.trim();

test("parseAlmanac", () => {
  const input = `
seeds: 79 14 55 13

seed-to-soil map:
50 98 2

soil-to-fertilizer map:
0 15 37
37 52 2
  `.trim();

  expect(parseAlmanac(input)).toEqual({
    seedNumbers: [79, 14, 55, 13],
    seedRanges: [
      { number: 79, range: 14 },
      { number: 55, range: 13 },
    ],
    maps: [
      {
        name: "seed-to-soil",
        mappings: [{ destinationRangeStart: 50, sourceRangeStart: 98, rangeLength: 2 }],
      },
      {
        name: "soil-to-fertilizer",
        mappings: [
          { destinationRangeStart: 0, sourceRangeStart: 15, rangeLength: 37 },
          { destinationRangeStart: 37, sourceRangeStart: 52, rangeLength: 2 },
        ],
      },
    ],
  });
});

test("findMapping", () => {
  const seedToSoilMap = parseAlmanacMap(
    `
seed-to-soil map:
50 98 2
52 50 48
    `.trim()
  );

  expect(findMappingBySeedNumber(79, seedToSoilMap)).toEqual(81);
  expect(findMappingBySeedNumber(14, seedToSoilMap)).toEqual(14);
  expect(findMappingBySeedNumber(55, seedToSoilMap)).toEqual(57);
  expect(findMappingBySeedNumber(13, seedToSoilMap)).toEqual(13);
});

test("findFinalMapping", () => {
  const almanac = parseAlmanac(exampleInput);

  expect(findFinalMappingBySeedNumber(14, almanac)).toEqual(43);
  expect(findFinalMappingBySeedNumber(79, almanac)).toEqual(82);
  expect(findFinalMappingBySeedNumber(55, almanac)).toEqual(86);
  expect(findFinalMappingBySeedNumber(13, almanac)).toEqual(35);
});

test("findShortestLocationBySeedNumber", () => {
  expect(findShortestLocationBySeedNumber(exampleInput)).toEqual(35);
});

test("findShortestLocationBySeedRange", () => {
  expect(findShortestLocationBySeedRange(exampleInput)).toEqual(46);
});
