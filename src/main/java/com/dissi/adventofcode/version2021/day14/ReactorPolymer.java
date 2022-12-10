package com.dissi.adventofcode.version2021.day14;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day14.Polymerization.simulateSteps;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class ReactorPolymer {

    private static final String LOCATION = "/2021/day14/input.txt";

    @SolutionAnnotation(day = 14, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);
        long answer = simulateSteps(lines, 10);
        return "" + answer;
    }

}
