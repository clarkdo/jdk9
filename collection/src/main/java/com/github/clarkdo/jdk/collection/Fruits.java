package com.github.clarkdo.jdk.collection;

import java.util.List;

public class Fruits {

  @SafeVarargs
  public static final List<String> fruits(String...fruits) {
    return List.of(fruits);
  }
}
