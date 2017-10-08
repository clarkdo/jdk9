package com.github.clarkdo.jdk.stream;

import static java.time.LocalDate.of;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DatePickerTest {

  private List<LocalDate> dates = List.of(
      of(2017, 1, 1),
      of(2017, 3, 8),
      of(2017, 2, 14),
      of(2017, 4, 4),
      of(2017, 7, 1),
      of(2017, 8, 8),
      of(2017, 11, 11),
      of(2017, 12, 24)
  );

  @Test
  public void testPickFirstHalf() {
    List<LocalDate> results = DatePicker.firstHalf(dates);
    Assert.assertEquals(4, results.size());
    Assert.assertThat(results, hasItem(of(2017, 4, 4)));
  }

  @Test
  public void testSecondHalf() {
    List<LocalDate> results = DatePicker.secondHalf(dates);
    Assert.assertEquals(4, results.size());
    Assert.assertThat(results, hasItem(of(2017, 7, 1)));
    Assert.assertThat(results, hasItem(of(2017, 12, 24)));
  }

  @Test
  public void testPickNotNull() {
    Map<Integer, LocalDate> dates = new HashMap<>() {
      {
        put(1, LocalDate.of(2017, 1, 1));
        put(2, LocalDate.of(2017, 3, 8));
        put(3, null);
        put(3, LocalDate.of(2017, 1, 3));
      }
    };
    List<LocalDate> results = DatePicker.january(dates);
    Assert.assertEquals(2, results.size());
    Assert.assertThat(results, hasItems(
        of(2017, 1, 1),
        of(2017, 1, 3)));
  }
}
