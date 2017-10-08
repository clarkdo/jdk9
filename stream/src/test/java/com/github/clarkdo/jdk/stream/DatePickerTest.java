package com.github.clarkdo.jdk.stream;

import static org.hamcrest.CoreMatchers.hasItem;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatePickerTest {

  private List<LocalDate> dates = List.of(
      LocalDate.of(2017, 1, 1),
      LocalDate.of(2017, 3, 8),
      LocalDate.of(2017, 2, 14),
      LocalDate.of(2017, 4, 4),
      LocalDate.of(2017, 7, 1),
      LocalDate.of(2017, 8, 8),
      LocalDate.of(2017, 11, 11),
      LocalDate.of(2017, 12, 24)
  );

  @Test
  public void testPickFirstHalf() {
    List<LocalDate> results = DatePicker.pickFirstHalf(dates);
    Assert.assertEquals(4, results.size());
    Assert.assertThat(results, hasItem(LocalDate.of(2017, 4, 4)));
  }

  @Test
  public void testSecondHalf() {
    List<LocalDate> results = DatePicker.pickSecondHalf(dates);
    Assert.assertEquals(4, results.size());
    Assert.assertThat(results, hasItem(LocalDate.of(2017, 7, 1)));
    Assert.assertThat(results, hasItem(LocalDate.of(2017, 12, 24)));
  }
}
