package com.dissi.adventofcode.version2021.day08;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class DayEightSolutionTwo implements Answerable {

    private static final String LOCATION = "/2021/day8/input.txt";

    @Override
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

    @Override
    public int getDay() {
        return 8;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
