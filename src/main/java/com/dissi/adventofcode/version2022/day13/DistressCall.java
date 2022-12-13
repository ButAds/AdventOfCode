package com.dissi.adventofcode.version2022.day13;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DistressCall {

    private final String input;


    private static final String LOCATION = "/2022/day13/input.txt";

    public DistressCall() throws IOException {
        this.input = BufferUtils.getInputAsStringNoJoining(LOCATION);
    }

    @SolutionAnnotation(day = 13, section = 1, year = 2022)
    public int isTheListBeforetheOtherList() {
        String[] packets = input.split("\n\n");
        return IntStream.range(0, packets.length).parallel().map(index -> {
            String[] data = packets[index].split("\n");
            if (NumberList.parse(data[0]).compareTo(NumberList.parse(data[1])) > 0) {
                return index + 1;
            }
            return 0;
        }).sum();
    }

    @SolutionAnnotation(day = 13, section = 2, year = 2022)
    public int ohNumberTwoAndSixWhereArtThou() {

        List<NumberList> packets = Arrays.stream(input.split("\n"))
            .parallel()
            .filter(s -> !s.isBlank())
            .map(NumberList::parse)
            .collect(Collectors.toList());

        NumberList two = NumberList.parse("[[2]]");
        packets.add(two);
        NumberList six = NumberList.parse("[[6]]");
        packets.add(six);

        packets.sort(Collections.reverseOrder());

        int resultTwo = packets.indexOf(two) + 1;
        int resultSix = packets.indexOf(six) + 1;

        return resultTwo * resultSix;
    }
}
