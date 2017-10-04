package com.github.clarkdo.jdk.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;


public final class Http {

  public static void main(String[] args) {
    Http.ip();
  }

  /**
   * Get local ip by ipify synchronously with HTTP/1.1 protocol.
   * @return ip address String
   */
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

  /**
   * Get local ip by ipify asynchronously with HTTP/2 protocol.
   * @return a Future
   */
  public static final Future<HttpResponse<String>> asyncIp() {
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
