package com.dissi.adventofcode.version2021.day25;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SeaCumcumbers implements Answerable {

    private static final String LOCATION = "/2021/day25/input.txt";

    @Override
    public int getDay() {
        return 25;
    }

    @Override
    public int getSection() {
        return 1;
    }

    @Override
    public String getAnswer() throws IOException {
        List<String> inputs = getInputAsStringList(LOCATION);

        FloorMap map = FloorMap.fromString(inputs);
        int steps = 0;
        int moved;
        do {
            moved = map.tick();
            ++steps;
        } while (moved != 0);

        return "" + steps;
    }
}
