package com.infra.dsold;

import java.util.HashMap;
import java.util.Map;

import com.infra.ds.NameVote;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BallotBox {

  Map<String, Integer> ballotVotes;

  public BallotBox() {
    ballotVotes = new HashMap<>();
    //initCandidates();
  }

  private void calculateVotes(List<NameVote> votes) {
    // iterate through each voter's vote
    // add it to the candidate by key
    for(NameVote vote : votes) {
      HashMap<String, Integer> candidateVote = null;//vote.getAllVotes();
      for(String candidate : candidateVote.keySet()) {
        Integer currVotesForCandidate = ballotVotes.getOrDefault(candidate, 0);
        currVotesForCandidate += candidateVote.get(candidate);
        
        ballotVotes.put(candidate, currVotesForCandidate);
      }

    }
  }

  /*
  * Return all the candidates in a list in decreasing order of their votes
  */
  public List<String> findWinner(List<NameVote> votes) {
    // iterate through each voter's vote
    // find the max value
    // declare the winner from key
    System.out.println("no. of votes " + votes.size());
    System.out.println("votes " + votes.toString());
    calculateVotes(votes);
    System.out.println("ballotvotes " + ballotVotes.toString());

    List<CandidateVote> candidateVotes = new ArrayList<>();

    for(String candidate : ballotVotes.keySet()) {
      Integer currVotes = ballotVotes.get(candidate);
      candidateVotes.add(new CandidateVote(candidate, currVotes));
    }

    Collections.sort(candidateVotes, Collections.reverseOrder());
    List<String> winners = new ArrayList<String>();
    for(CandidateVote candidateVote : candidateVotes) {
      winners.add(candidateVote.name);
    }

    Collections.sort(candidateVotes, new CandidateVoteVotesComparator());
    System.out.println("sorted votes lo 2 hi " + candidateVotes.toString());

    Comparator<CandidateVote> candidateVote_Vote_Name_Comparator = Comparator.comparing(CandidateVote::getVotes).reversed().thenComparing(CandidateVote::getName);
    Collections.sort(candidateVotes, candidateVote_Vote_Name_Comparator);
    System.out.println("sorted votes H2L, name L2H " + candidateVotes.toString());

    Collections.sort(candidateVotes, new CandidateVoteNameComparator());
    System.out.println("sorted names lo 2 hi " + candidateVotes.toString());

    return winners;
  }
}