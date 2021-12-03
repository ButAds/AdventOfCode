package com.butads.adventofcode.version2021.day.two;

import static com.butads.adventofcode.BufferUtils.getInputAsStream;

import com.butads.adventofcode.version2021.Answerable;
import java.io.BufferedReader;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class Dive implements Answerable {

    private static final String LOCATION = "/2021/day2/input.txt";

    public String getAnswer() throws IOException {
        BufferedReader inputAsStream = getInputAsStream(LOCATION);

        int horizontal = 0;
        int depth = 0;

        String line;
        while ((line = inputAsStream.readLine()) != null) {

            String[] directionAndSpeed = line.split(" ");
            String direction = directionAndSpeed[0];
            int value = Integer.parseInt(directionAndSpeed[1]);
            switch (direction) {
                case "down" -> depth += value;
                case "up" -> depth -= value;
                case "forward" -> horizontal += value;
            }
        }
        return "" + (depth * horizontal);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
