package com.dissi.adventofcode.version2024.day09;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;

public class DiskFragmenter {

    private final String data;

    public DiskFragmenter() {
        this.data = BufferUtils.getInputAsString(2024, 9, false);
    }

    @SolutionAnnotation(day = 9, section = 1, year = 2024)
    public long solve() {
        return Disk.defragment(data, false).checkSum();
    }

    @SolutionAnnotation(day = 9, section = 2, year = 2024)
    public long fragOne() {
        return Disk.defragment(data, true).checkSum();
    }
}
