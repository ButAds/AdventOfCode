package com.dissi.adventofcode.version2021.day06;

import static com.dissi.adventofcode.version2021.day06.Fish.getAmountOfFish;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class FishDayByte {

    private static final String LOCATION = "/2021/day6/input.txt";

    @SolutionAnnotation(day = 6, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        return "" + getAmountOfFish(256, BufferUtils.getInputAsString(LOCATION));
    }

}
