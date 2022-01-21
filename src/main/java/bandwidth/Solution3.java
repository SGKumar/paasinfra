package bandwidth;

import java.util.Arrays;
import java.util.Comparator;

public class Solution3 {
  
	/*
	 * Solution-3
	 * input format is : [start-time, end-time, bandwidth]
	 * Assume for a show, start-time < end-time
	 * with 0-based index for array
	 * Normalize start/end times to a single array with bandwidth
	 * Input array
	 *    { 800, 1200, 4 },
				{ 600, 800, 6 },
				{ 900, 1400, 5 },
   * processed as 
				{ 600, 6},
				{ 800, -6},
	 * 		{ 800, 4 },
				{ 900, 5},
	 *    { 1200, -4},
				{1400, -5},
	 */
	public static int maxBandwidth3(int[][] shows) {

		int[][] timeBWs = buildNormalizedShowInfo(shows);

		sortNormalizedTimes(timeBWs);

		int currBW = 0, maxBW = 0;
		for(int ndx = 0; ndx < timeBWs.length; ndx++) {
			// y-axis cumulatively starts/ends with 0 
			// y-axis has a series of peaks, highest peak is the highest Bandwidth 
			currBW += timeBWs[ndx][1];
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
	private static int[][] buildNormalizedShowInfo(int[][] shows) {
		int[][] times = new int[shows.length*2][2];
		for(int i = 0; i < shows.length; i++) {
			times[2*i][0] = shows[i][0];
			times[2*i][1] = shows[i][2];

			times[2*i+1][0] = shows[i][1];
			times[2*i+1][1] = -1*shows[i][2];
		}
		return times;
	}

	/*
	 * input format is : [start-time, bandwidth], [end-time, -bandwidth] etc.,
	 * with 0-based index [0, 1]
	 * sort on (end-time ascending order, start-time ascending order)
	 */
	private static void sortNormalizedTimes(int[][] shows) {
		//System.out.println("Before: " + Arrays.deepToString(shows));
		Arrays.sort(shows, new Comparator<int[]>() {
			@Override
			public int compare(int[] row1, int[] row2) {
				if (row1[0] > row2[0]) {
					return 1;
				}
				if (row1[0] == row2[0]) {
					if (row1[1] > row2[1]) {
						return 1;
					}
				}
				return -1;
			}
		});
	}
}
