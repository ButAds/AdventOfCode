package com.dissi.adventofcode.version2022.day06;

import static com.dissi.adventofcode.version2022.day06.Tuner.getTunePosition;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;

public class TuningMachine {

    private static final String LOCATION = "/2022/day6/input.txt";

    @SolutionAnnotation(day = 6, section = 1, year = 2022)
    public String getAnswer() throws IOException {
        String data = BufferUtils.getInputAsStringNoJoining(LOCATION);
        int markerPosition = getTunePosition(data, 4);
        return "" + markerPosition;
    }
}
