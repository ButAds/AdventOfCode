package com.dissi.adventofcode.version2021.day16;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class HelloVersionMyOldFriend implements Answerable {

    private static final String LOCATION = "/2021/day16/input.txt";

    @Override
    public String getAnswer() throws IOException {
        String inputAsString = BufferUtils.getInputAsString(LOCATION);
        Packet p = new Packet(PacketUtils.toBinaryStringNotation(inputAsString), 0);
        return "" + p.getVersionSum();
    }

    @Override
    public int getDay() {
        return 16;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
