package com.github.clarkdo.jdk.collection;

import java.util.Set;

public class Animals {

  @SafeVarargs
  public static final Set<String> animals(String...animals) {
    return Set.of(animals);
  }
}
