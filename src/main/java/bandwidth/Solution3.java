package bandwidth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution3 {
  
	/*
	 * Solution-3
	 * input format is : [start-time, end-time, bandwidth]
	 * Assume for a show, start-time < end-time
	 * with 0-based index for array
	 * Normalize start/end times to a single array with bandwidth
	 * Input array
	 *    { 800, 1200, 4 },
	 *    { 600, 800, 6 },
	 *    { 900, 1400, 5 },
	 * processed as 
	 *    { 600, 6},
	 *    { 800, -6},
	 * 		{ 800, 4 },
	 *    { 900, 5},
	 *    { 1200, -4},
	 *    {1400, -5},
	 */
	public static long maxBandwidth3(long[][] shows) {

		List<ChannelShowInfo> timeBWs = buildNormalizedShowInfo(shows);
		//System.out.println("3 B4: " + timeBWs.toString());

		Collections.sort(timeBWs);
		//System.out.println("3 A4: " + timeBWs.toString());

		long currBW = 0, maxBW = 0;
		for(ChannelShowInfo timeBW : timeBWs) {
			// y-axis cumulatively starts/ends with 0 
			// y-axis has a series of peaks, highest peak is the highest Bandwidth 
			currBW += timeBW.bandwidth;
			maxBW = Math.max(currBW, maxBW);
		}

		return maxBW;
	}

	/*
	 * input format is : [start-time, end-time, bandwidth]
	 * Assume for a show, start-time < end-time
	 * with 0-based index for array
	 * where start-time(show-m) = end-time(show-n), end-time(show-n) will appear earlier.
	 * output format is [start/end time, bandwidth as +/- based on start/end time]
	 */
	private static List<ChannelShowInfo> buildNormalizedShowInfo(long[][] shows) {

		List<ChannelShowInfo> channelShows = new ArrayList<>();
		for(int i = 0; i < shows.length; i++) {
			channelShows.add(new ChannelShowInfo(shows[i][0], shows[i][2]));
			channelShows.add(new ChannelShowInfo(shows[i][1], -shows[i][2]));
		}
		return channelShows;
	}
}
