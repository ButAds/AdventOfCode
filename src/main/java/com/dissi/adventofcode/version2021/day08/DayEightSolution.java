package com.dissi.adventofcode.version2021.day08;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class DayEightSolution {

    private static final String LOCATION = "/2021/day8/input.txt";

    @SolutionAnnotation(day = 8, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> inputAsStringList = getInputAsStringList(LOCATION);
        int amount = 0;
        for (String s : inputAsStringList) {
            List<DigitalDisplay> displays = DisplayParser.parseLine(s);
            amount += displays.stream().skip(10)
                .filter(digitalDisplay ->
                    digitalDisplay.getOnlineSegments() == 2 ||
                        digitalDisplay.getOnlineSegments() == 3 ||
                        digitalDisplay.getOnlineSegments() == 4 ||
                        digitalDisplay.getOnlineSegments() == 7).count();
        }

        return "" + amount;
    }

}
