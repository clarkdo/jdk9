package com.github.clarkdo.jdk.collection;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FruitsTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testFruits() {
    List<String> fruits = Fruits.fruits("apple", "lemon", "banana", "pineapple");
    assertNotNull(fruits);
    assertEquals(fruits.size(), 4);
    assertThat(fruits, hasItems("apple", "lemon", "banana", "pineapple"));
  }

  @Test(expected = NullPointerException.class)
  public void testNullFruits() {
    Fruits.fruits("apple", null, "banana", "pineapple");
  }

  @Test
  public void testImmutableFruits() {
    List<String> fruits = Fruits.fruits("apple", "lemon", "banana", "pineapple");
    thrown.expect(UnsupportedOperationException.class);
    fruits.add("peach");
  }

  @Test
  public void testUnmodifiableFruits() {
    List<String> fruits = new ArrayList<>(Arrays.asList("apple", "lemon", "banana", "pineapple"));
    List<String> unmodFruits = Collections.unmodifiableList(fruits);

    assertEquals(unmodFruits.size(), 4);
    assertEquals(unmodFruits, fruits);

    fruits.add("peach");

    assertNotNull(fruits);
    assertEquals(fruits.size(), 5);
    assertThat(fruits, hasItem("peach"));

    thrown.expect(UnsupportedOperationException.class);
    unmodFruits.add("strawberry");
  }

  @Test
  public void testDuplicateFruits() {
    List<String> fruits = Fruits.fruits("apple", "apple", "lemon", "banana", "pineapple");
    assertNotNull(fruits);
    assertEquals(fruits.size(), 5);
  }

  @Test
  public void testValueFruits() {
    List<String> fruits = Fruits.fruits("apple", "lemon", "banana", "pineapple");
    List<String> fruitsNew = Fruits.fruits("apple", "lemon", "banana", "pineapple");
    assertFalse(fruits == fruitsNew);
    // and every member of the given fruits is contained in fruitsNew
    assertEquals(fruits, fruitsNew);
  }
}
