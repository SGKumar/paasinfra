package com.atlassian.old.design.ratelimit1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RateLimitParams {

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE) 
  private static final int MAX_LIMIT = 5;

  // let's say 5 calls per second
  TimeUnit timeUnit;

  int maxLimit;
  AtomicInteger isUpdating;
  int availableLimit;
  int creditLimit;
  long lastChecked;

  private void init(int limit, TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
    this.maxLimit = limit;
    this.availableLimit = 0;
    this.lastChecked = System.currentTimeMillis();
  } 
  public RateLimitParams() {
    init(MAX_LIMIT, TimeUnit.SECONDS);
  }

  public RateLimitParams(int limit) {
    init(limit, TimeUnit.SECONDS);
  }

  public RateLimitParams(int limit, TimeUnit windowSize) {
    init(limit, windowSize);
  }

  public static void main(String[] params) {
    System.out.printf("millisecs in secs = %d nanos in secs = %d\n", TimeUnit.SECONDS.toMillis(1), TimeUnit.SECONDS.toNanos(1));
    System.out.println(TimeUnit.SECONDS.convert(500, TimeUnit.MILLISECONDS));
  }
}
