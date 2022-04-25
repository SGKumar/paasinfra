package com.atlassian.old.ds;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class VoteCounter {

  List<String> findWinner(List<Vote> votes) {

    Map<String, Integer> allVotes = countAllVotes(votes);

    List<NameVote> finalVotes = new ArrayList<NameVote>();
    for(String candidate : allVotes.keySet()) {
      NameVote nameVote = new NameVote(candidate, allVotes.get(candidate));
      finalVotes.add(nameVote);
    }

    Collections.sort(finalVotes, Collections.reverseOrder());

    List<String> names = new ArrayList<>();
    for(int ndx = 0; ndx < finalVotes.size(); ndx++) {
      names.add(finalVotes.get(ndx).getName());
    }
    return names;
  }

  private Map<String, Integer> countAllVotes(List<Vote> votes) {
    HashMap<String, Integer> allVotes = new HashMap<>();
    for(Vote vote : votes) {

      Map<String, Integer> personVotes = vote.getVotes();
      for(String candidate : personVotes.keySet()) {
        allVotes.putIfAbsent(candidate, 0);
        Integer voteCount = allVotes.get(candidate);
        voteCount += personVotes.get(candidate);
      }
    }
    return allVotes;
  }

  public static void main(String[] args) {
    System.out.println("/users /users " + matchesPattern("/users", "/users"));
    System.out.println("users/5 users/** " + matchesPattern("users/5", "users/**"));
    System.out.println("users/5/orders users/* " + matchesPattern("users/5/orders", "users/*"));
    System.out.println("users/5/orders users/** " + matchesPattern("users/5/orders", "users/**"));
    //System.out.println("users/ users/** " + fitsTemplate("users/", "users/**"));
    //System.out.println("users/ users/** " + fitsTemplate("users/", "users/**"));
    //System.out.println("users/ users/** " + fitsTemplate("users/", "users/**"));

    //FileSystems.getDefault().getPathMatcher("glob").

  }

  private static boolean matchesPattern(String path, String pattern) {

    return FileSystems.getDefault()
                      .getPathMatcher("glob:" + pattern)
                      .matches(Paths.get(path));

  }
}