package com.atlassian.old.design.ratelimit1;

import java.util.concurrent.ConcurrentHashMap;

public abstract class RateLimiterBase {

  protected static ConcurrentHashMap<String, RateLimitParams> custLimits;
  static {
    custLimits = new ConcurrentHashMap<>();
    custLimits.putIfAbsent("cust1", new RateLimitParams());
    custLimits.putIfAbsent("cust2", new RateLimitParams());
    custLimits.putIfAbsent("cust3", new RateLimitParams());
  }
  protected RateLimitParams custLimit;

  protected RateLimiterBase() {
  }

  protected RateLimitParams initParamsAndGet(final String customerId) {
    if(!custLimits.containsKey(customerId)) {
      System.out.println("Initializing .....");
      custLimits.putIfAbsent(customerId, new RateLimitParams());
    }
    RateLimitParams custLimit = custLimits.get(customerId);
    return custLimit;
  }

  public abstract boolean allowed(final String customerId);
}