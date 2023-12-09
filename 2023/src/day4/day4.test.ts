import { calcPoints, calcTotalScratchCards, parseScratchCard, tallyWonCopies } from "./day4.ts";

const exampleInput = `
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
`.trim();

test("parseScratchCard", () => {
  expect(parseScratchCard("Card   1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")).toEqual({
    id: 1,
    winningCards: [41, 48, 83, 86, 17],
    cards: [83, 86, 6, 31, 17, 9, 48, 53],
    copies: 1,
  });
});

test("calcPoints", () => {
  expect(calcPoints([])).toBe(0);
  expect(calcPoints([10])).toBe(1);
  expect(calcPoints([1, 1])).toBe(2);
  expect(calcPoints([1, 1, 1])).toBe(4);
  expect(calcPoints([1, 1, 1, 1])).toBe(8);
  expect(calcPoints([1, 1, 1, 1, 1])).toBe(16);
});

test("updateCopies", () => {
  const scratchCards = exampleInput.split("\n").map(parseScratchCard);

  scratchCards.forEach((card) => tallyWonCopies(card, scratchCards));

  expect(scratchCards.map((card) => ({ id: card.id, copies: card.copies }))).toEqual([
    {
      id: 1,
      copies: 1,
    },
    {
      id: 2,
      copies: 2,
    },
    {
      id: 3,
      copies: 4,
    },
    {
      id: 4,
      copies: 8,
    },
    {
      id: 5,
      copies: 14,
    },
    {
      id: 6,
      copies: 1,
    },
  ]);
});

test("calcTotalScratchCards", () => {
  expect(calcTotalScratchCards(exampleInput.split("\n"))).toBe(30);
});
