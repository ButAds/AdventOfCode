package com.dissi.adventofcode.version2020.day01;

import static com.dissi.adventofcode.BufferUtils.getInputAsIntList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class RepairThree {

    private static final String LOCATION = "/2020/day1/input.txt";

    @SolutionAnnotation(day = 1, section = 2, year = 2020)
    public String getAnswer() throws IOException {
        List<Integer> intList = getInputAsIntList(LOCATION);

        return "" + calculate(intList);
    }

    public int calculate(List<Integer> intList) {
        for (int start = 0; start < intList.size(); start++) {
            int first = intList.get(start);
            for (int secondTest = start + 1; secondTest < intList.size(); secondTest++) {
                int second = intList.get(secondTest);
                for (int last = secondTest + 1; last < intList.size(); last++) {
                    if (first + second + intList.get(last) == 2020) {
                        return first * second * intList.get(last);
                    }
                }
            }
        }
        return 0;
    }
}
