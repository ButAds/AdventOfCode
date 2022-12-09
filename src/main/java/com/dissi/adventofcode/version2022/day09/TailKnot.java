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
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class TailKnot implements Answerable {

    private static final String LOCATION = "/2022/day9/input.txt";

    public String getAnswer() throws IOException {
        List<Action> actions = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(Action::parse)
            .toList();

        Position head = new Position(0, 0);
        Position[] nineTails = Stream.generate(Position::new)
            .limit(9)
            .toArray(Position[]::new);
        Set<Position> visitedTails = new HashSet<>();
        for (Action a : actions) {
            for (int step = 0; step < a.steps(); step++) {
                head = slither(head, a.direction());
                nineTails[0] = keepUpTail(head, nineTails[0]);
                for (int i = 0; i < nineTails.length - 1; i++) {
                    nineTails[i + 1] = keepUpTail(nineTails[i], nineTails[i + 1]);
                }
                visitedTails.add(nineTails[nineTails.length - 1]);
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
        return 2;
    }

    @Override
    public int getYear() {
        return 2022;
    }

}
