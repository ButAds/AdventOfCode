package com.dissi.adventofcode.helpers;

import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CountMap<K> {

    private final Map<K, Long> counters = new HashMap<>();

    public void increment(K increment) {
        this.increment(increment, 1);
    }

    public void increment(K increment, long incrementer) {
        counters.merge(increment, incrementer, Long::sum);
    }

    public Map<K, Long> getOccurances() {
        return counters;
    }

    public Long getValue(K key) {
        return counters.get(key);
    }
}
