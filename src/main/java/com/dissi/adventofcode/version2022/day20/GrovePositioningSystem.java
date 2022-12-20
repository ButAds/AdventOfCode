package com.dissi.adventofcode.version2022.day20;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GrovePositioningSystem {

    private final List<Integer> data;

    public GrovePositioningSystem() {
        this.data = BufferUtils.getInputAsIntList(2022, 20, false);
    }

    @SolutionAnnotation(day = 20, section = 1, year = 2022)
    public long decryptData() {
        return decryptData(1, 1L);
    }

    @SolutionAnnotation(day = 20, section = 2, year = 2022)
    public long decryptDataWithKey() {
        return decryptData(10, 811589153L);
    }

    private record CryptoEntry(long value, int originalIndex) {

    }

    public long decryptData(int rounds, long decryptionKey) {
        List<CryptoEntry> cryptoEntries = IntStream.range(0, data.size())
            .mapToObj(i -> new CryptoEntry(data.get(i) * decryptionKey, i))
            .collect(Collectors.toList());

        List<CryptoEntry> processOrder = new ArrayList<>(cryptoEntries);

        for (int times = 0; times < rounds; times++) {
            for (CryptoEntry toMove : processOrder) {
                int currentIndex = cryptoEntries.indexOf(toMove);
                toMove = cryptoEntries.remove(currentIndex);

                int newPosition;

                if ((toMove.value + currentIndex) >= 0) {
                    newPosition = (int) (toMove.value + currentIndex) % cryptoEntries.size();
                } else {
                    newPosition = (int) (cryptoEntries.size() - (-(toMove.value + currentIndex)
                        % cryptoEntries.size()));
                }

                cryptoEntries.add((newPosition == 0 ? cryptoEntries.size() : newPosition), toMove);
            }
        }

        int fromIndex = cryptoEntries.indexOf(
            cryptoEntries.stream().parallel().filter(n -> n.value == 0).findFirst().orElseThrow());
        long v1 = cryptoEntries.get((fromIndex + 1000) % cryptoEntries.size()).value;
        long v2 = cryptoEntries.get((fromIndex + 2000) % cryptoEntries.size()).value;
        long v3 = cryptoEntries.get((fromIndex + 3000) % cryptoEntries.size()).value;
        return v1 + v2 + v3;
    }
}
