package com.dissi.adventofcode.version2021.day17;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class HighNoon {

    private static final String LOCATION = "/2021/day17/input.txt";

    @SolutionAnnotation(day = 17, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        String target = BufferUtils.getInputAsString(LOCATION);

        PoolBar poolBar = new PoolBar(target);
        return "" + poolBar.getMaxY();
    }

}
