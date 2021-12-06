package com.butads.adventofcode.version2020.day02;

import static com.butads.adventofcode.BufferUtils.getInputAsStringList;

import com.butads.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PhilosophyTwo implements Answerable {

    private static final String LOCATION = "/2020/day2/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> inputAsStringList = getInputAsStringList(LOCATION);
        long amount = inputAsStringList.stream().map(ComplexPassword::new).filter(ComplexPassword::valid).count();
        return "" + amount;
    }

    @Override
    public int getYear() {
        return 2020;
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
