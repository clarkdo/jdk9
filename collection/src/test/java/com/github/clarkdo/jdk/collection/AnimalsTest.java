package com.github.clarkdo.jdk.collection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Set;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class AnimalsTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testAnimals() {
    Set<String> animals = Animals.animals("cat", "dog", "giraffe", "kangaroo");
    assertNotNull(animals);
    assertEquals(animals.size(), 4);
    assertThat(animals, hasItems("cat", "dog", "giraffe", "kangaroo"));
  }

  @Test(expected = NullPointerException.class)
  public void testNullAnimals() {
    Animals.animals("cat", null, "giraffe", "kangaroo");
  }

  @Test
  public void testImmutableAnimals() {
    Set<String> animals = Animals.animals("cat", "dog", "giraffe", "kangaroo");
    thrown.expect(UnsupportedOperationException.class);
    animals.add("moose");
  }

  @Test
  public void testDuplicateAnimals() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(containsString("duplicate element: cat"));
    Animals.animals("cat", "cat", "dog", "giraffe", "kangaroo");
  }

  @Test
  public void testValueAnimals() {
    Set<String> animals = Animals.animals("cat", "dog", "giraffe", "kangaroo");
    Set<String> animalsNew = Animals.animals("cat", "dog", "giraffe", "kangaroo");
    assertFalse(animals == animalsNew);
    // and every member of the given animals is contained in animalsNew
    assertEquals(animals, animalsNew);
  }

}
