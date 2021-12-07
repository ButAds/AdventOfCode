package com.dissi.adventofcode.version2021.day06;

import static com.dissi.adventofcode.version2021.day06.Fish.getAmountOfFish;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class FishDayByte implements Answerable {

    private static final String LOCATION = "/2021/day6/input.txt";

    @Override
    public String getAnswer() throws IOException {
        return "" + getAmountOfFish(256, BufferUtils.getInputAsString(LOCATION));
    }

    @Override
    public int getDay() {
        return 6;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
