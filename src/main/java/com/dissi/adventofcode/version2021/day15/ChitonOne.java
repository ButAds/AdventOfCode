package com.dissi.adventofcode.version2021.day15;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class ChitonOne {

    private static final String LOCATION = "/2021/day15/input.txt";

    @SolutionAnnotation(day = 15, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);
        NeighbourFind neighbourFind = new NeighbourFind(lines, 1);

        return "" + neighbourFind.getMinimumRisk();
    }

}
