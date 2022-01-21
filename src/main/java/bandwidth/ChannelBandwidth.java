package bandwidth;

import java.util.List;
import java.util.ArrayList;
public class ChannelBandwidth {

	public static void main(String[] args) {
		int[][] showTimes = {
				{ 800, 1200, 4 },
				{ 600, 800, 6 },
				{ 900, 1400, 5 },
				//{ 1500, 1600, 11 },
				//{ 800, 900, 8 }
		};

		List<ChannelShowInfo> shows = new ArrayList<ChannelShowInfo>();
		for(int i = 0; i < showTimes.length; i++) {
			shows.add(new ChannelShowInfo(showTimes[i][0], showTimes[i][1], showTimes[i][2]));
		}

		System.out.println("Max bandwidth needed 3 = " + Solution3.maxBandwidth3(showTimes));
		System.out.println("Max bandwidth needed 1 = " + Solution1.maxBandwidth1(showTimes));

		System.out.println("Max bandwidth needed 2 = " + Solution2.maxBandwidth2(shows));
	}
}