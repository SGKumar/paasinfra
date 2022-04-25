package com.atlassian.dsold;

import java.util.Comparator;

public class CandidateVoteVotesComparator implements Comparator<CandidateVote> {

  @Override
  public int compare(CandidateVote o1, CandidateVote o2) {
    return o1.compareTo(o2);
  }
}
