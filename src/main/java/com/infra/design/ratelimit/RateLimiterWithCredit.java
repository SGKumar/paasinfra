package com.atlassian.old.design.ratelimit1;

import java.util.concurrent.ConcurrentHashMap;
//import java.util.stream.IntStream;


public class RateLimiterWithCredit {

  static ConcurrentHashMap<String, RateLimitParams> custLimits;
  RateLimitParams custLimit;

  public RateLimiterWithCredit() {
    super();
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

      //int counter = custLimit.getCounter().get();
     /* custLimit.get
      if(!custLimit.getCounter().compareAndSet(counter, counter+1)) {
        return false;
      }*/

      int newLimit = 0;
/*
      // Drip into current service bucket
      int newLimit = custLimit.getAvailableLimit();
      newLimit += custLimit.getDefaultLimit() * milliSecsPassed/1000;
      if(newLimit > custLimit.defaultLimit) {
        int oldCredits = custLimit.getCreditLimit();
        int newCredits =  oldCredits + newLimit - custLimit.getDefaultLimit();
        custLimit.setCreditLimit(newCredits);
        newLimit = custLimit.defaultLimit;
      }
      else if(newLimit == 0 && custLimit.getCreditLimit() > 0) {
        newLimit = 1;
        custLimit.setCreditLimit(custLimit.getCreditLimit() - 1);
      }*/
      System.out.printf("New Limit: %d Credits %d\n", newLimit, custLimit.getCreditLimit());
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

    RateLimiterDrip rateLimiter = new RateLimiterDrip();
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