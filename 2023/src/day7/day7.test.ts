import { calcTotalWinnings, sortHandsByStrength } from "./day7.ts";
import { CARD_STRENGTH, CARD_STRENGTH_WITH_JOKER, HAND_TYPE } from "./typesAndConstants.ts";
import { CamelCards } from "./CamelCards.ts";

const exampleInput = `
32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483
`
  .trim()
  .split("\n");

const camelCardsWithoutJoker = CamelCards({ jokerIsWildCard: false });
const camelCardsWithJoker = CamelCards({ jokerIsWildCard: true });

test("parseHand", () => {
  expect(camelCardsWithoutJoker.parseHand("32T3K 765")).toEqual({
    cards: ["3", "2", "T", "3", "K"],
    bid: 765,
    type: HAND_TYPE.ONE_PAIR,
  });
});

test("isXOfAKind", () => {
  expect(camelCardsWithoutJoker.isXOfAKind(["3", "3", "3", "3", "K"], 5)).toBe(false);
  expect(camelCardsWithoutJoker.isXOfAKind(["3", "3", "3", "3", "3"], 5)).toBe(true);

  expect(camelCardsWithoutJoker.isXOfAKind(["3", "3", "3", "3", "3"], 4)).toBe(false);
  expect(camelCardsWithoutJoker.isXOfAKind(["3", "3", "3", "K", "K"], 4)).toBe(false);
  expect(camelCardsWithoutJoker.isXOfAKind(["3", "3", "3", "3", "K"], 4)).toBe(true);

  expect(camelCardsWithJoker.isXOfAKind(["3", "3", "3", "J", "K"], 4)).toBe(true);
  expect(camelCardsWithJoker.isXOfAKind(["3", "3", "J", "J", "J"], 4)).toBe(true);
  expect(camelCardsWithJoker.isXOfAKind(["3", "3", "J", "J", "J"], 5)).toBe(true);
  expect(camelCardsWithJoker.isXOfAKind(["J", "J", "J", "J", "J"], 5)).toBe(true);
});

test("isFullHouse", () => {
  expect(camelCardsWithoutJoker.isFullHouse(["3", "3", "3", "3", "K"])).toBe(false);
  expect(camelCardsWithoutJoker.isFullHouse(["K", "K", "3", "3", "3"])).toBe(true);
  expect(camelCardsWithoutJoker.isFullHouse(["3", "3", "3", "K", "K"])).toBe(true);
  expect(camelCardsWithoutJoker.isFullHouse(["3", "3", "K", "K", "3"])).toBe(true);

  expect(camelCardsWithJoker.isFullHouse(["J", "3", "K", "K", "3"])).toBe(true);
  expect(camelCardsWithJoker.isFullHouse(["J", "3", "T", "6", "6"])).toBe(false);
});

test("isTwoPair", () => {
  expect(camelCardsWithoutJoker.isTwoPair(["3", "3", "3", "3", "K"])).toBe(false);
  expect(camelCardsWithoutJoker.isTwoPair(["K", "K", "3", "3", "3"])).toBe(false);
  expect(camelCardsWithoutJoker.isTwoPair(["K", "K", "3", "3", "A"])).toBe(true);
});

test("sortByHandStrength", () => {
  const handsWithoutJoker = camelCardsWithoutJoker.parseHands(exampleInput);
  const handsWithJoker = camelCardsWithJoker.parseHands(exampleInput);

  expect(
    sortHandsByStrength(handsWithoutJoker, CARD_STRENGTH).map((hand) => hand.cards.join(""))
  ).toStrictEqual(["32T3K", "KTJJT", "KK677", "T55J5", "QQQJA"]);
  expect(
    sortHandsByStrength(handsWithJoker, CARD_STRENGTH_WITH_JOKER).map((hand) => hand.cards.join(""))
  ).toStrictEqual(["32T3K", "KK677", "T55J5", "QQQJA", "KTJJT"]);
});

test("calcTotalWinnings", () => {
  expect(calcTotalWinnings(exampleInput, { jokerIsWildCard: false })).toBe(6440);
  expect(calcTotalWinnings(exampleInput, { jokerIsWildCard: true })).toBe(5905);
});
