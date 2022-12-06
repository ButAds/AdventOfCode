package com.dissi.adventofcode.version2022.day06;

import static com.dissi.adventofcode.version2022.day06.Tuner.getTunePosition;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class TuningMachine implements Answerable {

    private static final String LOCATION = "/2022/day6/input.txt";

    public String getAnswer() throws IOException {
        String data = BufferUtils.getInputAsStringNoJoining(LOCATION);
        int markerPosition = getTunePosition(data, 4);
        return "" + markerPosition;
    }

    @Override
    public int getDay() {
        return 6;
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
