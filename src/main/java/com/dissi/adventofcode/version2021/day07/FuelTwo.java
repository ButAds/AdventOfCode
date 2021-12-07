package com.dissi.adventofcode.version2021.day07;

import static com.dissi.adventofcode.BufferUtils.getSingleLineAsInt;
import static com.dissi.adventofcode.version2021.day07.Fuel.getFuel;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class FuelTwo implements Answerable {

    private static final String LOCATION = "/2021/day7/input.txt";

    public static long calculateFuel(long distance) {
        return (distance * (distance + 1)) / 2;
    }

    @Override
    public String getAnswer() throws IOException {
        return "" + getFuel(getSingleLineAsInt(LOCATION), FuelTwo::calculateFuel);
    }

    @Override
    public int getDay() {
        return 7;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
