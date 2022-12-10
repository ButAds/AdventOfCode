package com.dissi.adventofcode.version2021.day16;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class HelloVersionMyOldFriend {

    private static final String LOCATION = "/2021/day16/input.txt";

    @SolutionAnnotation(day = 16, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        String inputAsString = BufferUtils.getInputAsString(LOCATION);
        Packet p = new Packet(PacketUtils.toBinaryStringNotation(inputAsString), 0);
        return "" + p.getVersionSum();
    }

}
