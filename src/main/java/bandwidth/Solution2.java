package bandwidth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution2 {

	/*
	 * Solution-2
	 * input format is : [start-time, end-time, bandwidth]
	 * Assume for a show, start-time < end-time
	 * with 0-based index for array
	 */
	public static long maxBandwidth2(long[][] shows) {
		
		List<ChannelShowInfo> showBegins = new ArrayList<ChannelShowInfo>();
		List<ChannelShowInfo> showEnds = new ArrayList<ChannelShowInfo>();

		for(int i = 0; i < shows.length; i++) {
			showBegins.add(new ChannelShowInfo(shows[i][0], shows[i][2]));
			showEnds.add(new ChannelShowInfo(shows[i][1], shows[i][2]));
		}

		Collections.sort(showBegins);
		Collections.sort(showEnds);

		return maxBandwidth2(showBegins, showEnds);
	}

	private static long maxBandwidth2(List<ChannelShowInfo> begins, List<ChannelShowInfo> ends) {
		long currBW = 0, maxBW = 0;
		int numShows = begins.size();
		int firstinWindow = 0, curr = 0;
		while (firstinWindow < numShows && curr < numShows) {
			// figures sliding windows of shows where show times overlap
			// in a sliding window of n shows, show-n starts *before* show-1 ends
			// show(n+1) starts after show(1) ends
			// if show S1's end-time = show S2's start-time, we don't consider them as
			// overlapping

			if (ends.get(firstinWindow).epoch > begins.get(curr).epoch) {
				currBW += begins.get(curr).bandwidth;
				maxBW = Math.max(currBW, maxBW);
				curr++;
			} else {
				// the window slides fwd by one show, ignore the 1st show's needed bandwidth
				currBW -= ends.get(firstinWindow).bandwidth;
				firstinWindow++;
			}
		}
		return maxBW;
	}
}
