package com.dissi.adventofcode.version2022.day02;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class RockPaperStrategy {

    private static final String LOCATION = "/2022/day2/input.txt";

    @SolutionAnnotation(day = 2, section = 2, year = 2022)
    public String getAnswer() throws IOException {
        List<String> allLines = BufferUtils.getInputAsStringList(LOCATION);
        int amount = allLines.stream()
            .map(PlayRules::create)
            .mapToInt(playRules ->
                (playRules.pick() + 1) + playRules.moveResult() * 3)
            .sum();

        return "" + amount;
    }

}
