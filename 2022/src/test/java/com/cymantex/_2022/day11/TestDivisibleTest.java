package com.cymantex._2022.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestDivisibleTest {
  DivisibleTest divisibleTest = new DivisibleTest(9, 10, 10);

  @Test
  void calcRemainder() {
    Item item = new Item(10);
    item.addWorryLevelAddend(20);

    System.out.println(divisibleTest.calcRemainder(item));
    Assertions.assertEquals(3, divisibleTest.calcRemainder(item));
  }
}
