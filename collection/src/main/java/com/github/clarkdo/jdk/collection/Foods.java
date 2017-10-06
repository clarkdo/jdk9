package com.github.clarkdo.jdk.collection;

import java.util.Map;

public class Foods {

  @SafeVarargs
  public static final Map<String, String> foods(Map.Entry<String, String>...foods) {
    return Map.ofEntries(foods);
  }
}
