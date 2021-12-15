package com.dissi.adventofcode.version2021.day15;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ChitonLarge implements Answerable {

    private static final String LOCATION = "/2021/day15/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        NeighbourFind neighbourFind = new NeighbourFind(lines, 5);

        return "" + neighbourFind.getMinimumRisk();
    }

    @Override
    public int getDay() {
        return 15;
    }

    @Override
    public int getSection() {
        return 2;
    }

}
