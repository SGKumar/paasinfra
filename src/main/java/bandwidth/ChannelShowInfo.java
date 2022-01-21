package bandwidth;

import java.lang.Comparable;

public class ChannelShowInfo implements Comparable<ChannelShowInfo> {
  long startEpoch;
  long endEpoch;
  int bandwidth;

  /*public ShowInfo(int start, int end, int bw) {
    startEpoch = start;
    endEpoch = end;
    bandwidth = bw;
  }*/
  public ChannelShowInfo(long start, long end, int bw) {
    startEpoch = start;
    endEpoch = end;
    bandwidth = bw;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("[").append(startEpoch).append(",").append(endEpoch).append(",").append(bandwidth).append("]");
    return str.toString();
  }

  @Override
  public int compareTo(ChannelShowInfo showIn) {
    if (this.endEpoch > showIn.endEpoch) {
      return 1;
    }
    if (this.endEpoch == showIn.endEpoch) {
      if (this.startEpoch > showIn.startEpoch) {
        return 1;
      }
    }
    return -1;
  }
}