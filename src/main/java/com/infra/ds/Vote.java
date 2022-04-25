package com.atlassian.old.ds;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Vote {
  Map<String, Integer> votes;

  public Vote() {
    votes = new HashMap<>();
  }
  
}