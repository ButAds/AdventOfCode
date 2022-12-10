package com.dissi.adventofcode.version2021.day08;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class DayEightSolutionTwo {

    private static final String LOCATION = "/2021/day8/input.txt";

    @SolutionAnnotation(day = 8, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        List<String> inputAsStringList = getInputAsStringList(LOCATION);
        int total = 0;
        for (String s : inputAsStringList) {
            List<DigitalDisplay> displays = DisplayParser.parseLine(s);
            List<DigitalDisplay> outComeCombined = displays.subList(10, 14);
            Map<DigitalDisplay, Integer> outcomes = SegmentConnectionCalculator.getOutcomes(displays);
            int now = 0;
            now += outcomes.get(outComeCombined.get(0)) * 1000;
            now += outcomes.get(outComeCombined.get(1)) * 100;
            now += outcomes.get(outComeCombined.get(2)) * 10;
            now += outcomes.get(outComeCombined.get(3));
            total += now;

        }
        return "" + total;

    }

}
