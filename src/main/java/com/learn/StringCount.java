package com.coin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StringCount implements Comparable<StringCount> {
  String name;
  Integer count;

  // This will be an individual person's vote - there'll always be only a single call to 
  // vote for a particular candidate
  public StringCount(String candidate, int count) {
    this.name = candidate;
    this.count = count;
  } 

  /*
  @Override
  public String toString() {
    return String.format("[%s,%d]", name, votes);
  }*/

  @Override
  public int compareTo(StringCount in) {
    return this.count.compareTo(in.count);
  }
}
