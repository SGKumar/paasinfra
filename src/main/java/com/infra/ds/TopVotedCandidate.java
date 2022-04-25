package com.atlassian.old.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class TopVotedCandidate {

  int[] persons;
  int[] times;

  TreeMap<Integer, VoteCount> voteTimes;
  
  public TopVotedCandidate(int[] persons, int[] times) {
    this.persons = persons;
    this.times = times;

    voteTimes = new TreeMap<Integer, VoteCount>();
    buildVoteAtTime();
  }
  
  private void buildVoteAtTime() {
    PriorityQueue<VoteCount> queVotes = new PriorityQueue<VoteCount>(Collections.reverseOrder());
    HashMap<Integer, VoteCount> voteCounts = new HashMap<Integer, VoteCount>();

    for(int i = 0; i < persons.length; i++) {
      if(voteCounts.get(persons[i]) == null) {
        voteCounts.put(persons[i], new VoteCount(persons[i], 0, times[i]));
      }
      VoteCount voteCount = voteCounts.get(persons[i]);
      voteCount.setVotes(voteCount.getVotes()+1);
      voteCount.setTime(times[i]);
      System.out.println("voteCounts " + voteCounts);


      queVotes.add(new VoteCount(persons[i], voteCount.getVotes(), voteCount.getTime()));
      VoteCount topVote = queVotes.peek();
      System.out.println("topVote " + topVote);
      voteTimes.put(times[i], topVote);
    }

  }
  public int q(int t) {
    return voteTimes.floorEntry(t).getValue().getPersonId();
  }
  public static void main(String[] args) {
    TopVotedCandidate top = new TopVotedCandidate(new int[] {0, 1, 1, 0, 0, 1, 0}, new int[] {0, 5, 10, 15, 20, 25, 30});
    System.out.println("3 " + top.q(3));
    System.out.println("12 " + top.q(12));
    System.out.println("25 " + top.q(25));
    System.out.println("15 " + top.q(15));
    System.out.println("24 " + top.q(24));
    System.out.println("8 " + top.q(8));
  }

}