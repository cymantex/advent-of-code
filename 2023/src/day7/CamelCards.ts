import {
  CamelCardsRules,
  CARD_STRENGTH,
  CARD_STRENGTH_WITH_JOKER,
  CardName,
  Hand,
  HAND_TYPE,
  HandType,
} from "./typesAndConstants.ts";
import { countBy, parseInt, values } from "lodash";
import { calcTotalWinnings, sortHandsByStrength } from "./day7.ts";

export function CamelCards({ jokerIsWildCard }: CamelCardsRules) {
  const parseHands = (hands: string[]): Hand[] => hands.map((hand) => parseHand(hand));

  const parseHand = (hand: string): Hand => {
    const handTokens = hand.split(" ");
    const cards = handTokens[0].split("") as CardName[];
    const bid = parseInt(handTokens[1]);
    return { cards, bid, type: determineHandType(cards) };
  };

  const determineHandType = (cards: CardName[]): HandType => {
    if (isXOfAKind(cards, 5)) return HAND_TYPE.FIVE_OF_A_KIND;
    if (isXOfAKind(cards, 4)) return HAND_TYPE.FOUR_OF_A_KIND;
    if (isFullHouse(cards)) return HAND_TYPE.FULL_HOUSE;
    if (isXOfAKind(cards, 3)) return HAND_TYPE.THREE_OF_A_KIND;
    if (isTwoPair(cards)) return HAND_TYPE.TWO_PAIR;
    if (isXOfAKind(cards, 2)) return HAND_TYPE.ONE_PAIR;
    return HAND_TYPE.HIGH_CARD;
  };

  const countJokers = (cards: CardName[]): number =>
    jokerIsWildCard ? cards.filter((card) => card === "J").length : 0;

  const isXOfAKind = (cards: CardName[], x: number): boolean => {
    const xOfAKind = Math.max(...findDuplicateCardCounts(excludeJokers(cards)));
    const jokerCount = countJokers(cards);

    if (jokerCount === cards.length) return true;
    if (jokerCount > 0) {
      return xOfAKind + jokerCount >= x;
    }

    return xOfAKind === x;
  };

  const isFullHouse = (cards: CardName[]): boolean => {
    const duplicateCardCounts = findDuplicateCardCounts(excludeJokers(cards));
    if (duplicateCardCounts.length !== 2) return false;
    const threeOfAKind = Math.max(...duplicateCardCounts);
    const twoOfAKind = Math.min(...duplicateCardCounts);
    const jokerCount = countJokers(cards);
    if (jokerCount === 1 && threeOfAKind === 2) return threeOfAKind === 2 && twoOfAKind === 2;
    if (jokerCount === 1 && twoOfAKind === 1) return threeOfAKind === 3 && twoOfAKind === 1;
    return threeOfAKind === 3 && twoOfAKind === 2;
  };

  const isTwoPair = (cards: CardName[]): boolean => {
    if (isXOfAKind(cards, 4)) return false;
    const pairs = findDuplicateCardCounts(excludeJokers(cards)).filter((count) => count === 2);
    return pairs.length === 2;
  };

  const excludeJokers = (cards: CardName[]): CardName[] =>
    jokerIsWildCard ? cards.filter((card) => card !== "J") : cards;

  const findDuplicateCardCounts = (cards: CardName[]) => values(countBy(cards, (card) => card));

  return {
    parseHands,
    parseHand,
    findHandType: determineHandType,
    sortHandsByStrength,
    calcTotalWinnings,
    isXOfAKind,
    isFullHouse,
    isTwoPair,
    cardStrengths: jokerIsWildCard ? CARD_STRENGTH_WITH_JOKER : CARD_STRENGTH,
  };
}
