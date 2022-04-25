package com.atlassian.old.design.ratelimit1;

import java.util.stream.IntStream;

public class RateLimiterDrip extends RateLimiterBase {

  public RateLimiterDrip() {
    super();
  }

  public boolean allowed(final String customerId) {

    boolean allowCall = false;
    RateLimitParams custLimit = initParamsAndGet(customerId);

    synchronized(custLimit) {
      long currTime = System.currentTimeMillis();
      long milliSecsPassed = currTime - custLimit.getLastChecked();
      System.out.printf("milliSecsPassed %d\n", milliSecsPassed);
      custLimit.setLastChecked(currTime);
      
      // Drip into current service bucket
      // Available limit at most can be till maximum only
      int newLimit = custLimit.getAvailableLimit();
      newLimit += custLimit.getMaxLimit() * milliSecsPassed/custLimit.getTimeUnit().toMillis(1);
      newLimit = Math.min(newLimit, custLimit.getMaxLimit());

      System.out.println("New Limit: " + newLimit);
      if(newLimit >= 1) {
        allowCall = true;
        custLimit.setAvailableLimit(newLimit-1);
      }
    }
    return allowCall;
  }

  public static void main(String[] args) {

    /*
    IntStream.range(0, 10)
      .forEach(i -> System.out.printf("i: %d; thread: %s\n", i, Thread.currentThread().getName()));
      */
    
    IntStream.range(0, 10)
      .parallel()   
      .forEach(i -> System.out.printf("i: %d; thread: %s\n", i, Thread.currentThread().getName()));    

    RateLimiterDrip rateLimiter = new RateLimiterDrip();
    final String callerName = "cust1";

    try {
      /*IntStream.range(1, 5)
        .parallel()
        .forEach(i -> {
          for(int j = 0; j < 10; j++) {
            boolean isAllowed = rateLimiter.allowed(callerName);
            System.out.printf("i: %d; thread: %s: %b\n", i, Thread.currentThread().getName(), isAllowed);
            if(j%3 == 0) {
              //Thread.sleep(500);
            }
          }  
          //System.out.printf("%s: %s\n", loadBalancer.getClass().getSimpleName(), serverName);
      });
      */

      for(int i = 0; i < 15; i++) {
        System.out.println("Call allowed: " + rateLimiter.allowed(callerName));
        if(i%6 == 0) {
          Thread.sleep(1000);
        }
        if(i%3 == 0) {
          Thread.sleep(500);
        }
      }
    }
    catch(InterruptedException ex) {
      System.out.println(ex.toString());
    }

  }
}