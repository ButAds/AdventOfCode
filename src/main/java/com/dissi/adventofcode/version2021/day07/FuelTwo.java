package com.dissi.adventofcode.version2021.day07;

import static com.dissi.adventofcode.BufferUtils.getSingleLineAsInt;
import static com.dissi.adventofcode.version2021.day07.Fuel.getFuel;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class FuelTwo {

    private static final String LOCATION = "/2021/day7/input.txt";

    @SolutionAnnotation(day = 7, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        return "" + getFuel(getSingleLineAsInt(LOCATION), distance -> (distance * (distance + 1)) / 2);
    }
}
