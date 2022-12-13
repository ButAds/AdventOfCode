package com.dissi.adventofcode.version2022.day07;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class DirectoryDeleterWalker {

    private static final String LOCATION = "/2022/day7/input.txt";
    private final List<String> data;

    public DirectoryDeleterWalker() throws IOException {
        data = BufferUtils.getInputAsStringList(LOCATION);
    }

    @SolutionAnnotation(day = 7, section = 1, year = 2022)
    public long getSumOfAllBelowTreshold() {
        ElfDirectory root = ElfDirectory.createFromInput(data);

        return root.getAllDirectories().stream()
            .filter(elfDirectory -> elfDirectory.size() < 100_000)
            .mapToLong(ElfDirectory::size).sum();
    }


    @SolutionAnnotation(day = 7, section = 2, year = 2022)
    public long getSmallestToDelete() {
        ElfDirectory root = ElfDirectory.createFromInput(data);

        long currentSize = root.size();
        long currentDiskUtilization = 70_000_000 - currentSize;
        long neededSize = 30_000_000 - currentDiskUtilization;

        ElfDirectory smallest = root.getAllDirectories().stream()
            .sorted(Comparator.comparingLong(ElfDirectory::size))
            .filter(dir -> dir.size() > neededSize)
            .findFirst().orElseThrow();

        return smallest.size();
    }
}
