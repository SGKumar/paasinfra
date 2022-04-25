package com.atlassianold.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class NameVote implements Comparable<NameVote> {
  String name;
  Integer votes;

  public void addVote(String name, int vote) {
    this.name = name;
    this.votes = vote;
  }

  @Override
  public int compareTo(NameVote o) {
    return this.votes.compareTo(o.getVotes());
  }
}