package com.dissi.adventofcode.version2021.day12;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day12.PathingBuilder.countPaths;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class PathTroubles {

    private static final String LOCATION = "/2021/day12/input.txt";

    @SolutionAnnotation(day = 12, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);

        Map<String, List<String>> connectionMap = PathingBuilder.getConnectionMap(lines);

        return "" + countPaths(connectionMap, false);
    }

}
