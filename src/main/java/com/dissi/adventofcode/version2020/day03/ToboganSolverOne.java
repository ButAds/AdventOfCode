package com.dissi.adventofcode.version2020.day03;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2020.day03.Toboggan.traverse;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class ToboganSolverOne {

    private static final String LOCATION = "/2020/day3/input.txt";

    @SolutionAnnotation(day = 3, section = 1, year = 2020)
    public String getAnswer() throws IOException {
        List<String> treeList = getInputAsStringList(LOCATION);
        long amountOfTrees = traverse(treeList, 1, 3);
        return "" + amountOfTrees;
    }
}
