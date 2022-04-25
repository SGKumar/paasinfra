package com.infra.ds;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import com.infra.ds.NameVote;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class BallotBoxTest {

  //Voting ballot = new Voting();

  @DataProvider
  public static Object[][] dataProviderAdd() {
      // @formatter:off
      return new Object[][] {
              { 0, 0, 0 },
              { 1, 1, 2 },
              /* ... */
      };
  }
  
  @Test
  @UseDataProvider("dataProviderAdd")
  public void testAdd(int a, int b, int expected) {
      // Given:

      // When:
      int result = a + b;

      // Then:
      Assert.assertEquals(expected, result);
  }

  private List<NameVote> EmptyBallotList() {
    return new ArrayList<NameVote>();
  }

  private List<NameVote> SingleBallot1Vote() {
    List<NameVote> votes = new ArrayList<>();

    votes.add(new NameVote());
    votes.get(0).addVote("Amy", 2);
    return votes;
  }

  private List<NameVote> SingleBallotListVotesFor3() {
    List<NameVote> votes = new ArrayList<>();

    votes.add(new NameVote());
    votes.get(0).addVote("Amy", 2);
    votes.get(0).addVote("Ben", 3);
    votes.get(0).addVote("Cao", 1);
    return votes;
  }

  private List<NameVote> TenVotersVoteFor3() {
    List<NameVote> votes = new ArrayList<>();

    votes.add(new NameVote());
    votes.get(0).addVote("Amy", 2);
    votes.get(0).addVote("Ben", 3);
    votes.get(0).addVote("Cao", 1);

    votes.add(new NameVote());
    votes.get(1).addVote("Eve", 2);
    votes.get(1).addVote("Dan", 3);
    votes.get(1).addVote("Amy", 1);

    votes.add(new NameVote());
    votes.get(2).addVote("Dan", 2);
    votes.get(2).addVote("Eve", 3);
    votes.get(2).addVote("Cao", 1);

    votes.add(new NameVote());
    votes.get(2).addVote("Amy", 2);
    votes.get(2).addVote("Ben", 3);
    votes.get(2).addVote("Eve", 1);

    return votes;
  }
/*
    return new Object[][][] {
      {
        {"Amy", 2}, {"Ben", 3}, {"Cao", 1},
      },
      {
        {"Eve", 2}, {"Dan", 3}, {"Amy", 1},
      },
      {
        {"Dan", 2}, {"Eve", 3}, {"Cao", 1},
      },
      {
        {"Amy", 2}, {"Ben", 3}, {"Eve", 1},
      }
    };
  }

  private List<Vote> generateVotes(Object[][][] objs) {
    List<Vote> votesForCandidates = new ArrayList<>();
    for(Object[][] voterObjs : objs) {
      //System.out.println(Arrays.deepToString(voterObjs));
      Vote vote = new Vote();
      for(Object[] votes : voterObjs) {
        vote.addVote((String)votes[0], (Integer)votes[1]);
      }
      votesForCandidates.add(vote);
    }
    return votesForCandidates;
  }
  */

  @Test
  public final void ReturnWinnerForEmptyBallot() {
    List<NameVote> votes = EmptyBallotList();
    System.out.println(ballot.findWinner(votes));
  }

  @Test
  public final void ReturnWinnerFor1Voter1Candidate() {
    List<NameVote> votes = SingleBallot1Vote();
    System.out.println(ballot.findWinner(votes));
  }

  @Test
  public final void ReturnWinnerFor1Voter3Candidates() {
    List<NameVote> votes = SingleBallotListVotesFor3();
    System.out.println(ballot.findWinner(votes));
  }

  @Test
  public final void ReturnWinnerForTenVotersVoteFor3() {
    List<NameVote> votes = TenVotersVoteFor3();
    System.out.println(ballot.findWinner(votes));
  }

}