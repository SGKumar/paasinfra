package bandwidth;

//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
import bandwidth.util.FileUtil;
import org.junit.Assert;

import org.junit.Test;

import java.io.IOException;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Unit test class
public class SolutionTest {

    private String status = "Running %s with testcase-%d";

    @Test
    public void runTests() {
        File file = null;
        try {
            String fileName = "bandwidth.csv";
            file = FileUtil.getFile(getClass().getClassLoader(), fileName);

            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

            // first line is number of tests
            int tests = Integer.parseInt(lines.get(0));

            // for each test 
            // number of channels/parallel shows with 3 columns
            int numLines = 1;
            System.out.println("tests " + tests);
            for(int i = 0; i < tests; i++) {
                int channels = Integer.parseInt(lines.get(numLines));
                numLines++;

                int[][] channelsInfo = new int[channels][3];
                for(int channel = 0; channel < channels; channel++) {
                    List<Integer> s = Stream.of(lines.get(numLines).split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

                    for(int col = 0; col < 3; col++) {
                        channelsInfo[channel][col] = s.get(col);
                    }
                    numLines++;
                }

                int expected = Integer.parseInt(lines.get(numLines));
                numLines++;

                callSolution3(channelsInfo, i+1, expected);
                callSolution2(channelsInfo, i+1, expected);
                callSolution1(channelsInfo, i+1, expected);

            }
            //lines.forEach(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void callSolution1(int[][] channelsInfo, int test, int expected) {
        System.out.println(String.format(this.status, "Solution1", test));
        int bandwidth = Solution1.maxBandwidth1(channelsInfo);

        Assert.assertEquals(expected, bandwidth);
    }

    private void callSolution2(int[][] channelsInfo, int test, int expected) {
        System.out.println(String.format(this.status, "Solution2", test));

        List<ChannelShowInfo> shows = new ArrayList<ChannelShowInfo>();
        for(int i = 0; i < channelsInfo.length; i++) {
            shows.add(new ChannelShowInfo(channelsInfo[i][0], channelsInfo[i][1], channelsInfo[i][2]));
        }

        int bandwidth = Solution2.maxBandwidth2(shows);

        Assert.assertEquals(expected, bandwidth);
    }

    private void callSolution3(int[][] channelsInfo, int test, int expected) {
        System.out.println(String.format(this.status, "Solution3", test));
        int bandwidth = Solution3.maxBandwidth3(channelsInfo);

        Assert.assertEquals(expected, bandwidth);
    }
}