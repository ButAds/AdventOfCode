package com.dissi.adventofcode.version2022.day10;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.LinkedList;

public class SignalComputer {

    private static final String LOCATION = "/2022/day10/input.txt";

    private final LinkedList<Integer> signal;

    public SignalComputer() throws IOException {
        signal = createInput();
    }

    @SolutionAnnotation(day = 10, section = 1, year = 2022)
    public int section2() {
        return ((20 * signal.get(19))
            + (60 * signal.get(59))
            + (100 * signal.get(99))
            + (140 * signal.get(139))
            + (180 * signal.get(179))
            + (220 * signal.get(219)));
    }

    private LinkedList<Integer> createInput() throws IOException {
        LinkedList<Integer> calculating = new LinkedList<>();
        calculating.add(1);
        for (String s : BufferUtils.getInputAsStringList(LOCATION)) {
            String[] data = s.split(" ");
            calculating.add(calculating.getLast());
            if (data.length == 2) {
                calculating.add(calculating.getLast() + Integer.parseInt(data[1]));
            }
        }
        return calculating;
    }

    @SolutionAnnotation(day = 10, section = 2, year = 2022)
    public String getAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 6; y++) {
            sb.append('\n');
            for (int x = 0; x < 40; x++) {
                int signalAt = signal.get(y * 40 + x);
                if (x == signalAt || x == signalAt - 1 || x == signalAt + 1) {
                    sb.append("██");
                } else {
                    sb.append("░░");
                }
            }
        }
        return sb.toString();
    }


}
