package com.butads.adventofcode.version2021.day5;

import static com.butads.adventofcode.BufferUtils.getInputAsStringList;

import com.butads.adventofcode.version2021.Answerable;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class SecureHeatLocationDiagonal implements Answerable {

    private static final String LOCATION = "/2021/day5/input.txt";

    @Override
    public String getAnswer() throws IOException {
        HeatGrid grid = new HeatGrid(HeatLine.createHeatlines(getInputAsStringList(LOCATION)));
        return "" + grid.getPointsOverTwo(true).size();
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
