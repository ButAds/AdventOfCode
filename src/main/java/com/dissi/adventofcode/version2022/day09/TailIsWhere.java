package com.dissi.adventofcode.version2022.day09;

import static com.dissi.adventofcode.version2022.day09.Ekans.keepUpTail;
import static com.dissi.adventofcode.version2022.day09.Ekans.slither;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Position;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TailIsWhere {

    private static final String LOCATION = "/2022/day9/input.txt";

    @SolutionAnnotation(day = 9, section = 1, year = 2022)
    public String getAnswer() throws IOException {
        List<Action> actions = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(Action::parse)
            .toList();

        Position head = new Position(0, 0);
        Position tail = new Position(0, 0);
        Set<Position> visitedTails = new HashSet<>();
        for (Action a : actions) {
            for (int step = 0; step < a.steps(); step++) {
                head = slither(head, a.direction());
                tail = keepUpTail(head, tail);
                visitedTails.add(tail);
            }
        }

        return "" + visitedTails.size();
    }
}
