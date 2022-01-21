package bandwidth;
public class ChannelBandwidth {

	public static void main(String[] args) {
		long[][] showTimes = {
				{ 800, 1200, 4 },
				{ 600, 800, 6 },
				{ 900, 1400, 5 },
				//{ 1500, 1600, 11 },
				//{ 800, 900, 8 }
		};

		System.out.println("Max bandwidth needed 3 = " + Solution3.maxBandwidth3(showTimes));
		System.out.println("Max bandwidth needed 2 = " + Solution2.maxBandwidth2(showTimes));
	}
}