package com.dissi.adventofcode.version2020.day01;

import static com.dissi.adventofcode.BufferUtils.getInputAsIntList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class Repair {

    private static final String LOCATION = "/2020/day1/input.txt";

    @SolutionAnnotation(day = 1, section = 1, year = 2020)
    public String getAnswer() throws IOException {
        List<Integer> intList = getInputAsIntList(LOCATION);

        return "" + calculate(intList);
    }

    public int calculate(List<Integer> intList) {
        for (int start = 0; start < intList.size(); start++) {
            int now = intList.get(start);
            for (int currentTest = start + 1; currentTest < intList.size(); currentTest++) {
                if (intList.get(currentTest) + now == 2020) {
                    return intList.get(currentTest) * now;
                }
            }
        }
        return 0;
    }
}
