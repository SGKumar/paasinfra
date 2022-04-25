package com.stripe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Comparison {
  EQ("="), GTE(">="), GT(">"), LT("<"), LTE("<=");
  
  private static final Map<String, Comparison> ENUM_MAP;
  private final String name;
  
  private Comparison(String text) {
      this.name = text;
  }
  
  public String getName() {
      return this.name;
  }

  static {
    Map<String, Comparison> map = new ConcurrentHashMap<String, Comparison>();
    for (Comparison instance : Comparison.values()) {
        map.put(instance.getName().toLowerCase(),instance);
    }
    ENUM_MAP = Collections.unmodifiableMap(map);
  }

  public static Comparison get(String name) {
      return ENUM_MAP.get(name.toLowerCase());
  }
}