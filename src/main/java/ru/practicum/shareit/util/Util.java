package ru.practicum.shareit.util;

import java.util.Set;

public class Util {

  public static int findMaxId(Set<Integer> map) {
    return map.stream().mapToInt(number -> number).max().orElse(0);
  }
}
