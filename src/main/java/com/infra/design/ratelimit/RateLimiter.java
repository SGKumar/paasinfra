package com.atlassian.design.ratelimit1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;


public class RateLimiter {

  ConcurrentHashMap<String, RateLimitParams> custLimits;

  public RateLimiter() {
    custLimits = new ConcurrentHashMap<>();
  }

  public boolean allowed(final String customerId) {

    boolean allowCall = false;
    custLimits.putIfAbsent(customerId, new RateLimitParams());
    RateLimitParams custLimit = custLimits.get(customerId);

    synchronized(custLimit) {
      long currTime = System.currentTimeMillis();
      long milliSecsPassed = currTime - custLimit.getLastChecked();
      System.out.printf("milliSecsPassed %d\n", milliSecsPassed);
      custLimit.setLastChecked(currTime);

      // Drip into current service bucket
      int newLimit = custLimit.getAvailableLimit();
      //newLimit += milliSecsPassed * custLimit.getDefaultLimit()/(1000);
      newLimit += custLimit.getDefaultLimit() * milliSecsPassed/1000;
      if(newLimit > custLimit.defaultLimit) {
        custLimit.setCredits(custLimit.getCredits() + newLimit - custLimit.defaultLimit);
        newLimit = custLimit.defaultLimit;
      }
      else if(newLimit == 0 && custLimit.getCredits() > 0) {
        newLimit = 1;
        custLimit.setCredits(custLimit.getCredits() - 1);
      }
      System.out.printf("New Limit: %d Credits %d\n", newLimit, custLimit.getCredits());
      if(newLimit >= 1) {
        //System.out.println("New Limit: " + newLimit);
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

    /*
    IntStream.range(0, 10)
      .parallel()   
      .forEach(i -> System.out.printf("i: %d; thread: %s\n", i, Thread.currentThread().getName()));    
      */

    RateLimiter rateLimiter = new RateLimiter();
    final String serviceName = "userService";

    try {
      for(int i = 0; i < 14; i++) {
        System.out.println("Call allowed: " + rateLimiter.allowed(serviceName));
        if(i%6 == 0) {
          Thread.sleep(500);
        }
      }  
    }
    catch(InterruptedException ex) {
      System.out.println(ex.toString());
    }
  }
}