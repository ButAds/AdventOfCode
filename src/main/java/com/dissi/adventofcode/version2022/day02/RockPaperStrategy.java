package com.dissi.adventofcode.version2022.day02;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RockPaperStrategy implements Answerable {

    private static final String LOCATION = "/2022/day2/input.txt";

    public String getAnswer() throws IOException {
        List<String> allLines = BufferUtils.getInputAsStringList(LOCATION);
        int amount = allLines.stream()
            .map(PlayRules::create)
            .mapToInt(playRules ->
                (playRules.pick() + 1) + playRules.moveResult() * 3)
            .sum();

        return "" + amount;
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public int getSection() {
        return 1;
    }

    @Override
    public int getYear() {
        return 2022;
    }
}
