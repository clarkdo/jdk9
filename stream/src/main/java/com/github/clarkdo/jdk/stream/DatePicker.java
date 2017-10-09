package com.github.clarkdo.jdk.stream;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DatePicker {
  public static final LocalDate middle = LocalDate.of(2017, 7, 1);

  /**
   * pick date is in first half year.
   *
   * @param dates List
   * @return all dates
   */
  public static final List<LocalDate> firstHalf(List<LocalDate> dates) {
    return dates.stream()
        .takeWhile(d -> d.isBefore(middle))
        .collect(Collectors.toList());
  }

  /**
   * pick date is in second half year.
   *
   * @param dates List
   * @return all dates
   */
  public static final List<LocalDate> secondHalf(List<LocalDate> dates) {
    return dates.stream()
        .dropWhile(d -> d.isBefore(middle))
        .collect(Collectors.toList());
  }

  /**
   * pick date is not null.
   *
   * @param dates List
   * @return all not null dates
   */
  public static final List<LocalDate> january(Map<Integer, LocalDate> dates) {
    return dates.entrySet().stream()
        .flatMap(e -> Stream.ofNullable(e.getValue()))
        .filter(e -> e.getMonth() == Month.JANUARY)
        .collect(Collectors.toList());
  }

  /**
   * pick first dates which are weekdays(Just a example, not recommend use iterate like this).
   *
   * @param dates List
   * @return weekdays date
   */
  public static final List<LocalDate> firstWeekdays(List<LocalDate> dates) {
    return Stream
        .iterate(0, i -> dates.get(i).getDayOfWeek().getValue() <= 5, i -> ++i)
        .flatMap(i -> Stream.ofNullable(dates.get(i)))
        .collect(Collectors.toList());
  }
}
