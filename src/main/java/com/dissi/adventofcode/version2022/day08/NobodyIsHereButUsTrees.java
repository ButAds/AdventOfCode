package com.dissi.adventofcode.version2022.day08;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;

public class NobodyIsHereButUsTrees {

    private static final String LOCATION = "/2022/day8/input.txt";
    private final List<String> data;

    public NobodyIsHereButUsTrees() throws IOException {
        data = BufferUtils.getInputAsStringList(LOCATION);

    }

    @SolutionAnnotation(day = 8, section = 1, year = 2022)
    public int getAnswer() {

        VisibilityMap visibilityMap = new VisibilityMap(data);

        return visibilityMap.countVisible();
    }

    @SolutionAnnotation(day = 8, section = 2, year = 2022)
    public int arentTheTreesLovely() {

        VisibilityMap visibilityMap = new VisibilityMap(data);

        return visibilityMap.getMaxScenicScore();
    }

}
