package com.atlassian.dsold;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MyCounter {
  private int count;
  public void increment() {
      int temp = count;
      count = temp + 1;
  }
  // Getter for count
}
