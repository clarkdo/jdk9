package com.github.clarkdo.jdk.collection;

import static java.util.Map.entry;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FoodsTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testFoods() {
    Map<String, String> foods = Foods.foods(
        entry("bread", "🍞"),
        entry("hamburger", "🍔"),
        entry("pizza", "🍕"),
        entry("hotdog", "🌭"));
    assertNotNull(foods);
    assertEquals(foods.size(), 4);
    assertEquals(foods.get("bread"), "🍞");
    assertEquals(foods.get("hamburger"), "🍔");
    assertEquals(foods.get("pizza"), "🍕");
    assertEquals(foods.get("hotdog"), "🌭");
  }

  @Test(expected = NullPointerException.class)
  public void testNullFoods() {
    Foods.foods(
        entry("bread", "🍞"), null,
        entry("pizza", "🍕"),
        entry("hotdog", "🌭"));
  }

  @Test
  public void testImmutableFoods() {
    Map<String, String> foods = Foods.foods(
        entry("bread", "🍞"),
        entry("hamburger", "🍔"),
        entry("pizza", "🍕"),
        entry("hotdog", "🌭"));
    thrown.expect(UnsupportedOperationException.class);
    foods.put("sushi", "🍣");
  }

  @Test
  public void testUnmodifiableFoods() {
    HashMap<String, String> foods = new HashMap<>() {
      {
        put("bread", "🍞");
        put("hamburger", "🍔");
        put("pizza", "🍕");
        put("hotdog", "🌭");
      }
    };

    Map<String, String> unmodFoods = Collections.unmodifiableMap(foods);

    assertEquals(unmodFoods.size(), 4);
    assertEquals(unmodFoods, foods);
    foods.put("sushi", "🍣");

    assertEquals(unmodFoods.size(), 5);
    assertEquals(unmodFoods.get("sushi"), "🍣");
    assertEquals(unmodFoods, foods);

    thrown.expect(UnsupportedOperationException.class);
    unmodFoods.put("oden", "🍢");
  }

  @Test
  public void testDuplicateFoods() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(containsString("duplicate key: bread"));
    Foods.foods(
        entry("bread", "🍞"),
        entry("hamburger", "🍔"),
        entry("bread", "🥖"),
        entry("pizza", "🍕"),
        entry("hotdog", "🌭"));
  }

  @Test
  public void testValueFoods() {
    Map<String, String> foods = Foods.foods(
        entry("bread", "🍞"),
        entry("hamburger", "🍔"),
        entry("pizza", "🍕"),
        entry("hotdog", "🌭"));
    Map<String, String> foodsNew = Foods.foods(
        entry("bread", "🍞"),
        entry("hamburger", "🍔"),
        entry("pizza", "🍕"),
        entry("hotdog", "🌭"));
    assertFalse(foods == foodsNew);
    // and every member of the given foods is contained in foodsNew
    assertEquals(foods, foodsNew);
  }
}
