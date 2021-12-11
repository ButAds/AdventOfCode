package com.dissi.adventofcode.version2021.day11;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class OneHundredDaysLater implements Answerable {

    private static final String LOCATION = "/2021/day11/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        SquidGame squidGame = new SquidGame(lines);
        for (int i = 0; i < 100; i++) {
            squidGame.simulateDay();
        }

        return "" + squidGame.getFlashes();

    }

    @Override
    public int getDay() {
        return 11;
    }

    @Override
    public int getSection() {
        return 1;
    }

}
