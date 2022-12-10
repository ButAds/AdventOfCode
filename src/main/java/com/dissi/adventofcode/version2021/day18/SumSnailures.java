package com.dissi.adventofcode.version2021.day18;

import static com.dissi.adventofcode.version2021.day18.SnailForm.calculateSum;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class SumSnailures {

    private static final String LOCATION = "/2021/day18/input.txt";

    @SolutionAnnotation(day = 18, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = BufferUtils.getInputAsStringList(LOCATION);
        return "" + calculateSum(lines);
    }
}
