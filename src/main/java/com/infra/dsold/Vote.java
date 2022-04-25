package com.atlassian.dsold;

import java.util.HashMap;

public class Vote {
  HashMap<String, Integer> nameVote;

  private void initVars() {
    nameVote = new HashMap<>(); 
  }

  public Vote() {
    initVars();
  } 

  // This will be an individual person's vote - there'll always be only a single call to 
  // vote for a particular candidate
  public void addVote(String candidate, int vote) {
    nameVote.putIfAbsent(candidate, vote);
  } 

  public HashMap<String, Integer> getAllVotes() {
    return nameVote;
  }

  @Override
  public String toString() {
    return nameVote.toString();
  }
}
