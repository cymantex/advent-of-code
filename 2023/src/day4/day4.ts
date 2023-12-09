// noinspection JSUnusedGlobalSymbols
import { ScratchCard } from "./constantsAndTypes.ts";
import { intersection, parseInt } from "lodash";

export const printSolution = (scratchCards: string[]) => {
  console.log(calcTotalPoints(scratchCards));
  console.log(calcTotalScratchCards(scratchCards));
};

export const calcTotalPoints = (scratchCards: string[]): number =>
  scratchCards
    .map(parseScratchCard)
    .map(findWinningNumbers)
    .map(calcPoints)
    .reduce((total, points) => total + points, 0);

export const calcPoints = (winningNumbers: number[]): number =>
  winningNumbers.reduce((points) => (points === 0 ? 1 : points * 2), 0);

export const calcTotalScratchCards = (scratchCardsInput: string[]): number => {
  const scratchCards = scratchCardsInput.map(parseScratchCard);
  scratchCards.forEach((scratchCard) => tallyWonCopies(scratchCard, scratchCards));
  return scratchCards.reduce((totalCopies, scratchCard) => totalCopies + scratchCard.copies, 0);
};

export const tallyWonCopies = (scratchCard: ScratchCard, scratchCards: ScratchCard[]) =>
  scratchCards
    .slice(scratchCard.id, scratchCard.id + findWinningNumbers(scratchCard).length)
    .forEach((scratchCardToCopy) => {
      scratchCardToCopy.copies += scratchCard.copies;
    });

export const findWinningNumbers = (scratchCard: ScratchCard): number[] =>
  intersection(scratchCard.winningCards, scratchCard.cards);

export const parseScratchCard = (scratchCard: string): ScratchCard => {
  const scratchCardTokens = scratchCard.split(":");
  const id = parseInt(scratchCardTokens[0].split(" ").filter((token) => token)[1]);
  const cardTokens = scratchCardTokens[1].split("|");
  const winningCards = cardTokens[0]
    .split(" ")
    .filter((card) => card)
    .map(parseInt);
  const cards = cardTokens[1]
    .split(" ")
    .filter((card) => card)
    .map(parseInt);
  return { id, winningCards, cards, copies: 1 };
};
