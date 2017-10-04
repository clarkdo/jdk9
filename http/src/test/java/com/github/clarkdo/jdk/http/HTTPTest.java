package com.github.clarkdo.jdk.http;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import jdk.incubator.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

public class HttpTest {

  @Test
  public void testIp() {
    String ip = Http.ip();
    Future<HttpResponse<String>> response = Http.asyncIp();
    try {
      Thread.sleep(2000);
      if (response.isDone()) {
        String asyncIp = response.get().body();
        Assert.assertEquals(ip, asyncIp);
      } else {
        response.cancel(true);
        Assert.fail("Request took more than 2 seconds... cancelling.");
      }
    } catch (InterruptedException | ExecutionException e) {
      Assert.fail("exception: " + e.getMessage());
    }
  }
}
