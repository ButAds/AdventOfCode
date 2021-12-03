package com.butads.adventofcode.version2021.day.two;

import static com.butads.adventofcode.BufferUtils.getInputAsStream;

import com.butads.adventofcode.version2021.Answerable;
import java.io.BufferedReader;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class AimedDive implements Answerable {

    private static final String LOCATION = "/2021/day2/input.txt";

    public String getAnswer() throws IOException {
        BufferedReader inputAsStream = getInputAsStream(LOCATION);

        var horizontal = 0;
        var depth = 0;
        var aim = 0;

        String line;
        while ((line = inputAsStream.readLine()) != null) {
            String[] directionAndSpeed = line.split(" ");
            String direction = directionAndSpeed[0];
            int value = Integer.parseInt(directionAndSpeed[1]);

            switch (direction) {
                case "down" -> aim += value;
                case "up" -> aim -= value;
                case "forward" -> {
                    horizontal += value;
                    depth += (aim * value);
                }
            }
        }
        return "" + (horizontal * depth);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
