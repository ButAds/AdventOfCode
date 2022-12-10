package com.dissi.adventofcode.version2022.day07;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;

public class DirectoryWalker {

    private static final String LOCATION = "/2022/day7/input.txt";

    @SolutionAnnotation(day = 7, section = 1, year = 2022)
    public String getAnswer() throws IOException {
        List<String> data = BufferUtils.getInputAsStringList(LOCATION);
        ElfDirectory root = ElfDirectory.createFromInput(data);

        long answer = root.getAllDirectories().stream()
            .filter(elfDirectory -> elfDirectory.size() < 100000)
            .mapToLong(ElfDirectory::size).sum();
        return "" + answer;
    }

}
