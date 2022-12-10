package com.dissi.adventofcode.version2021.day14;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class BigreactorPolymer {

    private static final String LOCATION = "/2021/day14/input.txt";

    @SolutionAnnotation(day = 14, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);
        long answer = Polymerization.simulateSteps(lines, 40);
        return "" + answer;
    }


}
