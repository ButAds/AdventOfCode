package com.dissi.adventofcode.version2022.day09;

import static com.dissi.adventofcode.version2022.day09.Ekans.keepUpTail;
import static com.dissi.adventofcode.version2022.day09.Ekans.slither;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.helpers.Position;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class TailIsWhere implements Answerable {

    private static final String LOCATION = "/2022/day9/input.txt";

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

    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public int getSection() {
        return 1;
    }

    @Override
    public int getYear() {
        return 2022;
    }

}
