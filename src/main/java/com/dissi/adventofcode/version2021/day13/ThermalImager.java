package com.dissi.adventofcode.version2021.day13;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class ThermalImager {

    private static final String LOCATION = "/2021/day13/input.txt";

    @SolutionAnnotation(day = 13, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);
        Paper paper = new Paper(lines);

        while (!paper.allFoldDone()) {
            paper.fold();
        }

        return "\n" + paper;
    }

}
