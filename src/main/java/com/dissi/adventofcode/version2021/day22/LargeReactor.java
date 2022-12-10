package com.dissi.adventofcode.version2021.day22;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;

@Log

public class LargeReactor {

    private static final String LOCATION = "/2021/day22/input.txt";

    @SolutionAnnotation(day = 22, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        List<String> inputAsStringList = getInputAsStringList(LOCATION);

        List<WhatTheBox> boxes = inputAsStringList.stream().map(WhatTheBox::new).toList();

        return "" + ReactorSimulator.calculateVolume(boxes);
    }
}
