package com.dissi.adventofcode.version2022.day07;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DirectoryDeleterWalker implements Answerable {

    private static final String LOCATION = "/2022/day7/input.txt";

    public String getAnswer() throws IOException {
        List<String> data = BufferUtils.getInputAsStringList(LOCATION);
        ElfDirectory root = ElfDirectory.createFromInput(data);

        long answer = root.getAllDirectories().stream()
            .filter(elfDirectory -> elfDirectory.size() < 100000)
            .mapToLong(ElfDirectory::size).sum();
        return "" + answer;
    }

    @Override
    public int getDay() {
        return 7;
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
