package com.dissi.adventofcode.version2022.day02;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class RockPaperStrategy {

    private static final String LOCATION = "/2022/day2/input.txt";
    private final List<String> data;

    public RockPaperStrategy() throws IOException {
        this.data = BufferUtils.getInputAsStringList(LOCATION);
    }

    @SolutionAnnotation(day = 2, section = 2, year = 2022)
    public long rockPaperPerfectStrategy() {
        return data.parallelStream()
            .map(PlayRules::create)
            .mapToInt(playRules ->
                (playRules.pick() + 1) + playRules.moveResult() * 3)
            .sum();
    }

    @SolutionAnnotation(day = 2, section = 1, year = 2022)
    public long rockPaperStrategy() {
        return data.parallelStream()
            .map(PlayRules::create)
            .mapToInt(playRules ->
                (playRules.opponent() + 1) + playRules.movePerfectResult() * 3)
            .sum();
    }

}
