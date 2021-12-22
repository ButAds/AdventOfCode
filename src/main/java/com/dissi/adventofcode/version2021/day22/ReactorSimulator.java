package com.dissi.adventofcode.version2021.day22;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

@UtilityClass
@Log
public class ReactorSimulator {

    public static long calculateVolume(List<WhatTheBox> input) {
        List<WhatTheBox> placed = new LinkedList<>();
        for (WhatTheBox c : input) {
            List<WhatTheBox> newBoxes = placed.parallelStream()
                .map(p -> p.intersect(c, !p.isOn()))
                .filter(Optional::isPresent).map(Optional::get).toList();

            placed.addAll(newBoxes);
            if (c.isOn()) {
                placed.add(c);
            }
        }

        return placed.stream().parallel().mapToLong(WhatTheBox::volume).sum();
    }
}
