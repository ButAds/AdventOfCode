package com.dissi.adventofcode.version2022.day07;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class DirectoryDeleterWalker {

    private static final String LOCATION = "/2022/day7/input.txt";

    @SolutionAnnotation(day = 7, section = 2, year = 2022)
    public String getAnswer() throws IOException {
        List<String> data = BufferUtils.getInputAsStringList(LOCATION);
        ElfDirectory root = ElfDirectory.createFromInput(data);

        long currentSize = root.size();
        long currentDiskUtilization = 70000000 - currentSize;
        long neededSize = 30000000 - currentDiskUtilization;
        ElfDirectory smallest = root.getAllDirectories().stream()
            .sorted(Comparator.comparingLong(ElfDirectory::size))
            .filter(dir -> dir.size() > neededSize)
            .findFirst().orElseThrow();

        return "" + smallest.size();
    }
}
