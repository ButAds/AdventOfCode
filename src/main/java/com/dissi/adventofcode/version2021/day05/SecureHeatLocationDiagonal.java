package com.dissi.adventofcode.version2021.day05;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class SecureHeatLocationDiagonal implements Answerable {

    private static final String LOCATION = "/2021/day5/input.txt";

    @Override
    public String getAnswer() throws IOException {
        HeatGrid grid = new HeatGrid(HeatLine.createHeatlines(BufferUtils.getInputAsStringList(LOCATION)));
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
