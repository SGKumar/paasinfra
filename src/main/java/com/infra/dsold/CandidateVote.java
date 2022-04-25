package com.atlassian.dsold;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateVote implements Comparable<CandidateVote> {
  String name;
  Integer votes;

  // This will be an individual person's vote - there'll always be only a single call to 
  // vote for a particular candidate
  public CandidateVote(String candidate, int vote) {
    this.name = candidate;
    this.votes = vote;
  } 

  /*
  @Override
  public String toString() {
    return String.format("[%s,%d]", name, votes);
  }*/

  @Override
  public int compareTo(CandidateVote in) {
    return this.votes.compareTo(in.votes);
  }
}
