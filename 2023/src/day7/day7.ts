import { CamelCardsRules, CardName, Hand, HAND_TYPE_STRENGTH } from "./typesAndConstants.ts";
import { CamelCards } from "./CamelCards.ts";

export const printSolution = (input: string[]) => {
  console.log(calcTotalWinnings(input, { jokerIsWildCard: false }));
  console.log(calcTotalWinnings(input, { jokerIsWildCard: true }));
};

export const calcTotalWinnings = (input: string[], rules: CamelCardsRules): number => {
  const camelCards = CamelCards(rules);
  const hands = sortHandsByStrength(camelCards.parseHands(input), camelCards.cardStrengths);

  let totalWinnings = 0;

  for (let i = 0; i < hands.length; i++) {
    const rank = i + 1;
    const winnings = rank * hands[i].bid;
    totalWinnings += winnings;
  }

  return totalWinnings;
};

export const sortHandsByStrength = (
  hands: Hand[],
  cardStrengths: Record<CardName, number>
): Hand[] =>
  hands.sort((hand1, hand2) =>
    hand1.type === hand2.type
      ? compareCardStrengths(hand1, hand2, cardStrengths)
      : HAND_TYPE_STRENGTH[hand1.type] - HAND_TYPE_STRENGTH[hand2.type]
  );

const compareCardStrengths = (
  hand1: Hand,
  hand2: Hand,
  cardStrengths: Record<CardName, number>
) => {
  for (let i = 0; i < hand1.cards.length; i++) {
    const cardStrengthDiff = cardStrengths[hand1.cards[i]] - cardStrengths[hand2.cards[i]];

    if (cardStrengthDiff !== 0) {
      return cardStrengthDiff;
    }
  }

  return 0;
};
