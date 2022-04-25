package com.netflix.router;

import java.util.stream.IntStream;

//import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.assertTrue;

public class RouterTest {

  @Test
  public void RateLimiterGetFirst() {
    Router router = new Router();
    callLoadBalancer(loadBalancer);
  }

  @Test
  public void RandomLoadBalancer() {
    LoadBalancer loadBalancer = new RandomLoadBalancer();
    callLoadBalancer(loadBalancer);
  }

  private void callLoadBalancer(LoadBalancer loadBalancer) {
    /*
     * for(int i = 0; i < 10; i++) {
     * String serverName =
     * loadBalancer.getServerInstance("atlassian.confluenceService");
     * System.out.printf("%s: %s\n", loadBalancer.getClass().getSimpleName(),
     * serverName);
     * }
     */
    IntStream.range(0, 10)
        .parallel()
        .forEach(i -> {
          String serverName = loadBalancer.getServerInstance("atlassian.confluenceService");
          System.out.printf("i: %d; thread: %s %s: %s\n", i, Thread.currentThread().getName(),
              loadBalancer.getClass().getSimpleName(), serverName);
          // System.out.printf("%s: %s\n", loadBalancer.getClass().getSimpleName(),
          // serverName);
        });

  }
}
