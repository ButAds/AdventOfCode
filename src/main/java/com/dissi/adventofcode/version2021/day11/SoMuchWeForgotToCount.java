package com.dissi.adventofcode.version2021.day11;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SoMuchWeForgotToCount implements Answerable {

    private static final String LOCATION = "/2021/day11/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        SquidGame squidGame = new SquidGame(lines);

        while (!squidGame.isAllFlashes()) {
            squidGame.simulateDay();
        }

        return "" + squidGame.getDay();
    }

    @Override
    public int getDay() {
        return 11;
    }

    @Override
    public int getSection() {
        return 2;
    }

}
