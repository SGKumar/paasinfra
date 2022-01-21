package bandwidth;

import java.util.Collections;
import java.util.List;

public class Solution2 {

	/*
	 * Solution-2
	 * input format is : [start-time, end-time, bandwidth]
	 * Assume for a show, start-time < end-time
	 * with 0-based index for array
	 * This solution is same as Solution1, just show-info is captured in object
	 */
	public static int maxBandwidth2(List<ChannelShowInfo> shows) {
		System.out.println("Before2: " + shows.toString());
		Collections.sort(shows);
		System.out.println("After2: " + shows.toString());

		int currBW = 0, maxBW = 0;
		int numShows = shows.size();
		int firstinWindow = 0, curr = 0;
		while (firstinWindow < numShows && curr < numShows) {
			// figures sliding windows of shows where show times overlap
			// in a sliding window of n shows, show-n starts *before* show-1 ends
			// show(n+1) starts after show(1) ends
			// if show S1's end-time = show S2's start-time, we don't consider them as
			// overlapping

			if (shows.get(firstinWindow).endEpoch > shows.get(curr).startEpoch) {
				currBW += shows.get(curr).bandwidth;
				maxBW = Math.max(currBW, maxBW);
				curr++;
			} else {
				// the window slides fwd by one show, ignore the 1st show's needed bandwidth
				currBW -= shows.get(firstinWindow).bandwidth;
				firstinWindow++;
			}
		}
		return maxBW;
	}
}
