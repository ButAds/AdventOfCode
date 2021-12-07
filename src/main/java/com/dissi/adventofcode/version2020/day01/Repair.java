package com.dissi.adventofcode.version2020.day01;

import static com.dissi.adventofcode.BufferUtils.getInputAsIntList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Repair implements Answerable {

    private static final String LOCATION = "/2020/day1/input.txt";

    @Override
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

    @Override
    public int getYear() {
        return 2020;
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
