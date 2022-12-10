package com.dissi.adventofcode.version2021.day13;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class Origami {

    private static final String LOCATION = "/2021/day13/input.txt";

    @SolutionAnnotation(day = 13, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);
        Paper paper = new Paper(lines);
        paper.fold();
        return "" + paper.getVisibleDots();
    }

}
