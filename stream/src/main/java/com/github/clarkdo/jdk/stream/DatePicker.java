package com.github.clarkdo.jdk.stream;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public final class DatePicker {
  public static final LocalDate middle = LocalDate.of(2017, 7, 1);

  /**
   * pick date is in first half year.
   * @param dates List
   * @return all dates
   */
  public static final List<LocalDate> pickFirstHalf(List<LocalDate> dates) {
    return dates.stream()
        .takeWhile(d -> d.isBefore(middle))
        .collect(Collectors.toList());
  }

  /**
   * pick date is in second half year.
   * @param dates List
   * @return all dates
   */
  public static final List<LocalDate> pickSecondHalf(List<LocalDate> dates) {
    return dates.stream()
        .dropWhile(d -> d.isBefore(middle))
        .collect(Collectors.toList());
  }
}
