package com.dissi.adventofcode.version2022.day08;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;

public class NobodyIsHereButUsTrees {

    private static final String LOCATION = "/2022/day8/input.txt";

    @SolutionAnnotation(day = 8, section = 1, year = 2022)
    public String getAnswer() throws IOException {
        List<String> data = BufferUtils.getInputAsStringList(LOCATION);

        VisibilityMap visibilityMap = new VisibilityMap(data);

        return "" + visibilityMap.countVisible();
    }
}
