package com.cymantex._2022.day11;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ItemWorryLevelOperation extends BiConsumer<Operation, Item> {

}
