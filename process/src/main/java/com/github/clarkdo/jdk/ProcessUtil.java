package com.github.clarkdo.jdk;

import java.time.ZoneId;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProcessUtil {

  /**
   * Print the process related info into console.
   * @param p ProcessHandle
   */
  public static final void print(ProcessHandle p) {
    String na = "<not available>";
    ProcessHandle.Info info = p.info();
    System.out.printf("Process ID: %s%n", p.pid());
    System.out.printf("Command name: %s%n", info.command().orElse(na));
    System.out.printf("Command line: %s%n", info.commandLine().orElse(na));

    System.out.printf("Start time: %s%n",
        info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
            .toLocalDateTime().toString())
            .orElse(na));

    System.out.printf("Arguments: %s%n",
        info.arguments().map(a -> Stream.of(a)
            .collect(Collectors.joining(" ")))
            .orElse(na));

    System.out.printf("User: %s%n", info.user().orElse(na));
  }
}
