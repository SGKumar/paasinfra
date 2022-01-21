package bandwidth;

import java.lang.Comparable;

public class ChannelShowInfo implements Comparable<ChannelShowInfo> {
  long epoch;
  long bandwidth;

  /*public ShowInfo(int start, int end, int bw) {
    startEpoch = start;
    endEpoch = end;
    bandwidth = bw;
  }*/
  public ChannelShowInfo(long epoch, long bw) {
    this.epoch = epoch;
    bandwidth = bw;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("[").append(epoch).append(",").append(bandwidth).append("]");
    return str.toString();
  }

  @Override
  public int compareTo(ChannelShowInfo showIn) {
    if (this.epoch > showIn.epoch) {
      return 1;
    }
    if (this.epoch == showIn.epoch) {
      if (this.bandwidth > showIn.bandwidth) {
        return 1;
      }
    }
    return -1;
  }
}