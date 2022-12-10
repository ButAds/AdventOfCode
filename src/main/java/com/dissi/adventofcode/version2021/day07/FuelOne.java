package com.dissi.adventofcode.version2021.day07;

import static com.dissi.adventofcode.BufferUtils.getSingleLineAsInt;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class FuelOne {

    private static final String LOCATION = "/2021/day7/input.txt";

    @SolutionAnnotation(day = 7, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        return "" + Fuel.getFuel(getSingleLineAsInt(LOCATION), v -> v);
    }

}
