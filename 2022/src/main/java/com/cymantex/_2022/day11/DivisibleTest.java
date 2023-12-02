package com.cymantex._2022.day11;

import java.util.Arrays;

public record DivisibleTest(int divider, int trueMonkeyId, int falseMonkeyId) {

  /**
   * @param dividerInput Test: divisible by 11
   * @param ifTrueInput  If true: throw to monkey 1
   * @param ifFalseInput If false: throw to monkey 2
   */
  public static DivisibleTest from(String dividerInput, String ifTrueInput, String ifFalseInput) {
    return new DivisibleTest(
        extractFirstNumber(dividerInput),
        extractFirstNumber(ifTrueInput),
        extractFirstNumber(ifFalseInput));
  }

  public int nextMonkeyId(Item item) {
    return calcRemainder(item) == 0 ? trueMonkeyId : falseMonkeyId;
  }

  public int calcRemainder(Item item) {

    return this.calcRemainder(item);
  }

  private int calcRemainder(WorryLevelOperations operations, int worryLevelRemainder) {
    int multipliersRemainder = calcMultipliersRemainder(operations);
    int addendsRemainder = calcAddendsRemainder(operations);
    int addendSum = (calcOldAddendsRemainder(operations, worryLevelRemainder) + addendsRemainder) % divider;
    int multiplierProduct = (calcOldMultipliersRemainder(operations, worryLevelRemainder) * multipliersRemainder) % divider;
    // 10 + 20 mod 9 = ((10 mod 9) + (20 mod 9) mod 9)
    int worryLevelMod = (worryLevelRemainder * multiplierProduct) % divider;
    return (worryLevelMod + addendSum) % divider;
  }

  private int calcAddendsRemainder(WorryLevelOperations item) {
    // ([𝑎+𝑏] 𝑚𝑜𝑑 𝑚) = ([(𝑎 𝑚𝑜𝑑 𝑚)+(𝑏 𝑚𝑜𝑑 𝑚)] 𝑚𝑜𝑑 𝑚)
    return item.getAddends()
        .stream()
        .mapToInt(addend -> addend % divider)
        .reduce((addend1, addend2) -> (addend1 + addend2) % divider)
        .orElse(0);
  }

  private int calcMultipliersRemainder(WorryLevelOperations item) {
    // ([𝑎𝑏] 𝑚𝑜𝑑 𝑚) = ([(𝑎 𝑚𝑜𝑑 𝑚)(𝑏 𝑚𝑜𝑑 𝑚)] 𝑚𝑜𝑑 𝑚)
    return item.getMultipliers()
        .stream()
        .mapToInt(multiplier -> multiplier % divider)
        .reduce((multiplier1, multiplier2) -> (multiplier1 * multiplier2) % divider)
        .orElse(1);
  }

  private int calcOldAddendsRemainder(WorryLevelOperations item, int worryLevelRemainder) {
    return item.getWorryLevelAddends()
        .stream()
        .mapToInt(operations -> calcRemainder(operations, worryLevelRemainder))
        .map(a -> a % divider)
        .sum();
  }

  private int calcOldMultipliersRemainder(WorryLevelOperations item, int worryLevelRemainder) {
    return item.getWorryLevelMultipliers()
        .stream()
        .mapToInt(operations -> calcRemainder(operations, worryLevelRemainder))
        .reduce((item1Remainder, item2Remainder) -> (item1Remainder * item2Remainder) % divider)
        .orElse(1);
  }

  private static Integer extractFirstNumber(String input) {
    return Arrays
        .stream(input.split(" "))
        .filter(string -> string.matches("[0-9]+"))
        .map(Integer::parseInt)
        .findFirst()
        .orElseThrow();
  }
}
