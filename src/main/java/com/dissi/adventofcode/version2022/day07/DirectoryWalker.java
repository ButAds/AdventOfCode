package com.dissi.adventofcode.version2022.day07;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DirectoryWalker implements Answerable {

    private static final String LOCATION = "/2022/day7/input.txt";

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

    @Override
    public int getDay() {
        return 7;
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
