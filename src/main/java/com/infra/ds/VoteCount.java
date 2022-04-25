package com.atlassian.old.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class VoteCount implements Comparable<VoteCount> {
  int personId;
  int votes;
  int time;

  @Override
  public int compareTo(VoteCount o) {
    if(this.votes < o.votes) {
      return -1;
    }
    else if(this.votes == o.votes) {
      if(this.time < o.time) {
        return -1;
      }
    }
    return 1;
  }
}