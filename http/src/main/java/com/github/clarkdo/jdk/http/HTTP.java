package com.github.clarkdo.jdk9.http;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public final class HTTP {

  public static final String ip() {
    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = null;
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("https://api.ipify.org"))
          .GET()
          .version(HttpClient.Version.HTTP_1_1)
          .build();
      response = client.send(request, HttpResponse.BodyHandler.asString());
    } catch (URISyntaxException | InterruptedException | IOException e) {
      e.printStackTrace();
    }
    return response.body();
  }

  public static final Future<HttpResponse<String>> asyncIP() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = null;
    try {
      request = HttpRequest.newBuilder()
          .uri(new URI("https://api.ipify.org"))
          .GET()
          .version(HttpClient.Version.HTTP_2)
          .build();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    CompletableFuture<HttpResponse<String>> response = client
        .sendAsync(request, HttpResponse.BodyHandler.asString());
    return response;
  }
}
