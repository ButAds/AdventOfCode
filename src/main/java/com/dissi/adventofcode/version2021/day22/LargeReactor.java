package com.dissi.adventofcode.version2021.day22;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class LargeReactor implements Answerable {

    private static final String LOCATION = "/2021/day22/input.txt";


    @Override
    public int getDay() {
        return 22;
    }

    @Override
    public int getSection() {
        return 2;
    }

    @Override
    public String getAnswer() throws IOException {
        List<String> inputAsStringList = getInputAsStringList(LOCATION);

        List<WhatTheBox> boxes = inputAsStringList.stream().map(WhatTheBox::new).toList();

        return "" + ReactorSimulator.calculateVolume(boxes);
    }
}
