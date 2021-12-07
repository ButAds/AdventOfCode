package com.dissi.adventofcode.version2021.day07;

import static com.dissi.adventofcode.BufferUtils.getSingleLineAsInt;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class FuelOne implements Answerable {

    private static final String LOCATION = "/2021/day7/input.txt";

    public static long calculateFuel(long distance) {
        return distance;
    }

    @Override
    public String getAnswer() throws IOException {

        return "" + Fuel.getFuel(getSingleLineAsInt(LOCATION), FuelOne::calculateFuel);
    }

    @Override
    public int getDay() {
        return 7;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
