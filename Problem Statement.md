Problem Statement

Winner of an election
ppl r going to vote

/**
 * For a list of votes, return an ordered set of candidate in descending order of their votes.
 */ 
List<String> findWinner(List<Vote> votes)

Ppl can/will vote for multiple candidates
5 candidates - A, B, C, D, E 
3, 2, 1 - 

Do we A, B => A  

3s each candidate got

shd be able to decide on strategies to break tie 

Amy
Ben
Cao
Dan
Eve

Ganesh votes:
[(Amy, 3), (Dan, 2), (Eve, 1)]

Deigo votes:
[(Eve, 3), (Cao, 2), (Dan, 1)]

Votes tallying
Will be in a hashmap
<Candidate, votes>

