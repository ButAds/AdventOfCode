package com.dissi.adventofcode.version2021.day05;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class SecureHeatLocation {

    private static final String LOCATION = "/2021/day5/input.txt";

    @SolutionAnnotation(day = 5, section = 1, year = 2021)
    public String getAnswer() throws IOException {
        HeatGrid grid = new HeatGrid(HeatLine.createHeatlines(BufferUtils.getInputAsStringList(LOCATION)));

        return "" + grid.getPointsOverTwo(false).size();
    }
}
