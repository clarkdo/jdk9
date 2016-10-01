package com.github.clarkdo.jdk.test.http;

import com.github.clarkdo.jdk.http.HTTP;
import jdk.incubator.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HTTPTest {
  @Test
  public void testIP() {
    String ip = HTTP.ip();
    Future<HttpResponse<String>> response = HTTP.asyncIP();
    try {
      Thread.sleep(2000);
      if(response.isDone()) {
        String asyncIP = response.get().body();
        Assert.assertEquals(ip, asyncIP);
      } else {
        response.cancel(true);
        Assert.fail("Request took more than 2 seconds... cancelling.");
      }
    } catch (InterruptedException | ExecutionException e) {
      Assert.fail("exception: " + e.getMessage());
    }
  }
}
