package com.github.clarkdo.jdk.process;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

public class ProcessUtilTest {

  @Test
  public void testPrintCurrent() {
    ProcessUtil.print(ProcessHandle.current());
  }

  @Test
  public void testExit() {
    try {
      new ProcessBuilder()
        .command("java", "-version")
        .inheritIO()
        .start()
        .toHandle()
        .onExit().thenAccept((handle) -> {
          System.out.println("Process exited.");
          ProcessUtil.print(handle);
          Assert.assertNotNull(handle);
        }).get();
    } catch (InterruptedException | ExecutionException | IOException e) {
      Assert.fail("exception: " + e.getMessage());
    }
  }
}
