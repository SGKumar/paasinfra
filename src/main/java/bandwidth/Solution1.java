package bandwidth;

import java.util.Arrays;
import java.util.Comparator;

public class Solution1 {
  
	/*
	 * Solution-1
	 * input format is : [start-time, end-time, bandwidth]
	 * Assume for a show, start-time < end-time
	 * with 0-based index for array
	 */
	public static int maxBandwidth1(int[][] shows) {
		sortOnEndTimesThenStartTimes(shows);

		int currBW = 0, maxBW = 0;
		int numShows = shows.length;
		int firstinWindow = 0, curr = 0;
		while (firstinWindow < numShows && curr < numShows) {
			// figures sliding windows of shows where show times overlap
			// in a sliding window of n shows, show-n starts *before* show-1 ends
			// show(n+1) starts after show(1) ends
			// if show S1's end-time = show S2's start-time, we don't consider them as
			// overlapping

			if (shows[firstinWindow][1] > shows[curr][0]) {
				currBW += shows[curr][2];
				maxBW = Math.max(currBW, maxBW);
				curr++;
			} else {
				// the window slides fwd by one show, ignore the 1st show's needed bandwidth
				currBW -= shows[firstinWindow][2];
				firstinWindow++;
			}

		}
		return maxBW;
	}

	/*
	 * input format is : [start-time, end-time, bandwidth]
	 * with 0-based index [0, 1, 2]
	 * sort on (end-time ascending order, start-time ascending order)
	 */
	private static void sortOnEndTimesThenStartTimes(int[][] shows) {
		System.out.println("Before: " + Arrays.deepToString(shows));
		Arrays.sort(shows, new Comparator<int[]>() {
			@Override
			public int compare(int[] row1, int[] row2) {
				if (row1[1] > row2[1]) {
					return 1;
				}
				if (row1[1] == row2[1]) {
					if (row1[0] > row2[0]) {
						return 1;
					}
				}
				return -1;
			}
		});
		System.out.println("After: " + Arrays.deepToString(shows));
	}

}
