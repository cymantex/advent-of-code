export const CARD_STRENGTH = {
  A: 14,
  K: 13,
  Q: 12,
  J: 11,
  T: 10,
  "9": 9,
  "8": 8,
  "7": 7,
  "6": 6,
  "5": 5,
  "4": 4,
  "3": 3,
  "2": 2,
} as const;
export const CARD_STRENGTH_WITH_JOKER = {
  ...CARD_STRENGTH,
  J: 1,
};
export type CardName = keyof typeof CARD_STRENGTH;

export const HAND_TYPE = {
  FIVE_OF_A_KIND: "FIVE_OF_A_KIND",
  FOUR_OF_A_KIND: "FOUR_OF_A_KIND",
  FULL_HOUSE: "FULL_HOUSE",
  THREE_OF_A_KIND: "THREE_OF_A_KIND",
  TWO_PAIR: "TWO_PAIR",
  ONE_PAIR: "ONE_PAIR",
  HIGH_CARD: "HIGH_CARD",
} as const;
export type HandType = keyof typeof HAND_TYPE;
export const HAND_TYPE_STRENGTH: Record<HandType, number> = {
  FIVE_OF_A_KIND: 7,
  FOUR_OF_A_KIND: 6,
  FULL_HOUSE: 5,
  THREE_OF_A_KIND: 4,
  TWO_PAIR: 3,
  ONE_PAIR: 2,
  HIGH_CARD: 1,
} as const;

export interface Hand {
  cards: CardName[];
  bid: number;
  type: HandType;
}

export interface CamelCardsRules {
  jokerIsWildCard: boolean;
}
