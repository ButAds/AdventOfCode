package com.dissi.adventofcode.version2020.day03;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2020.day03.Toboggan.traverse;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ToboganSolverTwo implements Answerable {

    private static final String LOCATION = "/2020/day3/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> treeList = getInputAsStringList(LOCATION);
        long amountOfTrees = traverse(treeList, 1, 1) *
            traverse(treeList, 1, 3) *
            traverse(treeList, 1, 5) *
            traverse(treeList, 1, 7) *
            traverse(treeList, 2, 1);
        return "" + amountOfTrees;
    }

    @Override
    public int getYear() {
        return 2020;
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
