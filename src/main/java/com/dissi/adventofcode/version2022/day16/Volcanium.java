package com.dissi.adventofcode.version2022.day16;

import static java.lang.Integer.MIN_VALUE;
import static java.util.Comparator.comparingInt;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Volcanium {

    private static final String LOCATION = "/2022/day16/input.txt";

    private final List<Integer[]> graph = new ArrayList<>();
    private final List<Integer> flows = new ArrayList<>();
    private final int start;

    record Valve(String name, List<String> to, int flow) {

    }

    public Volcanium() throws IOException {
        List<Valve> valves = BufferUtils.getInputAsStringList(LOCATION)
            .stream()
            .map(s -> {
                String name = s.substring(6, 8);
                String rate = s.split("=")[1];
                int rateData = Integer.parseInt(rate.substring(0, rate.indexOf(";")));
                List<String> to = Arrays.stream(s.substring(s.lastIndexOf("valve"))
                        // Remove valves
                        .replaceFirst("s", "")
                        .replaceFirst("valve", "")
                        .split(","))
                    .map(String::trim)
                    .toList();
                return new Valve(name, to, rateData);
            })
            .sorted(comparingInt(o -> o.flow))
            .toList();
        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i = 0; i < valves.size(); i++) {
            nameToIndex.put(valves.get(i).name, i);
            flows.add(valves.get(i).flow);
        }
        for (Valve valve : valves) {
            graph.add(valve.to.stream().map(nameToIndex::get).toArray(Integer[]::new));
        }

        start = nameToIndex.get("AA");

    }

    record Choice(Integer location, Integer bitOps, Integer flow, Integer combined) {

    }

    @SolutionAnnotation(day = 16, section = 1, year = 2022)
    public int underPressure() {
        List<Choice> states = new ArrayList<>();
        states.add(new Choice(start, 0, 0, 0));
        Map<Integer, Integer> bestCollection = new HashMap<>();
        int max = 0;

        for (int round = 1; round <= 29; round++) {
            List<Choice> nstates = new ArrayList<>();
            for (Choice c : states) {
                int uniqueLocAndDepth = (c.location << 16) + c.bitOps;
                int projected = c.combined + c.flow * (30 - round + 1);
                if (bestCollection.getOrDefault(uniqueLocAndDepth, MIN_VALUE) > projected + 1) {
                    continue;
                }

                if (flows.get(c.location) > 0 && (c.bitOps & (1 << c.location)) == 0) {
                    int nbits = c.bitOps | (1 << c.location);
                    int nflow = c.flow + flows.get(c.location);
                    uniqueLocAndDepth = (c.location << 16) + nbits;
                    projected = c.combined + c.flow + nflow * (30 - round);
                    if (projected + 1 > bestCollection.getOrDefault(uniqueLocAndDepth, MIN_VALUE)) {
                        nstates.add(new Choice(c.location, nbits, nflow, c.combined + c.flow));
                        bestCollection.put(uniqueLocAndDepth, projected + 1);
                        if (projected > max) {
                            max = projected;
                        }
                    }
                }

                for (int dst : graph.get(c.location)) {
                    uniqueLocAndDepth = (dst << 16) + c.bitOps;
                    projected = c.combined + c.flow * (30 - round + 1);
                    if (projected + 1 > bestCollection.getOrDefault(uniqueLocAndDepth, MIN_VALUE)) {
                        nstates.add(new Choice(dst, c.bitOps, c.flow, c.combined + c.flow));
                        bestCollection.put(uniqueLocAndDepth, projected + 1);
                        if (projected > max) {
                            max = projected;
                        }
                    }
                }
            }

            states = nstates;
        }

        return max;
    }

    record ChoiceFant(Integer location, Integer fantLocation, Integer bitOps, Integer flow, Integer combined) {

    }

    record FantLoc(int loc, int bitOps, int flow) {

    }

    @SolutionAnnotation(day = 16, section = 2, year = 2022)
    public int flowLephant() {
        List<ChoiceFant> states = new ArrayList<>();
        states.add(new ChoiceFant(start, start, 0, 0, 0));
        Map<Integer, Integer> bestLoc = new HashMap<>();
        int max = 0;
        for (int round = 1; round <= 25; round++) {
            List<ChoiceFant> nstates = new ArrayList<>();
            int pc = 0;
            int permutationOps = 0;
            int previousAcc = -1;
            states.sort(comparingInt(f -> (f.location << 21) + (f.fantLocation << 15) + f.bitOps));
            for (ChoiceFant l : states) {
                int code = (l.location << 21) + (l.fantLocation << 15);
                int projected = l.combined + l.flow * (26 - round + 1);
                if (bestLoc.getOrDefault(code + l.bitOps, MIN_VALUE) > projected + 1
                    || pc == code
                    && (permutationOps & l.bitOps) == l.bitOps
                    && projected < previousAcc) {
                    continue;
                }

                pc = code;
                permutationOps = l.bitOps;
                previousAcc = projected;
                List<FantLoc> tmp = new ArrayList<>();
                if (flows.get(l.fantLocation) > 0 && (l.bitOps & (1 << l.fantLocation)) == 0) {
                    int nbits = l.bitOps | (1 << l.fantLocation);
                    tmp.add(new FantLoc(l.fantLocation, nbits, l.flow + flows.get(l.fantLocation)));
                }

                for (int dst : graph.get(l.fantLocation)) {
                    tmp.add(new FantLoc(dst, l.bitOps, l.flow));
                }

                for (FantLoc fl : tmp) {
                    if (flows.get(l.location) > 0 && (fl.bitOps & (1 << l.location)) == 0) {
                        int nbits = fl.bitOps | (1 << l.location);
                        int nflow = fl.flow + flows.get(l.location);
                        code = l.location <= fl.loc ? (l.location << 21) + (fl.loc << 15)
                            : (fl.loc << 21) + (l.location << 15);
                        code += nbits;
                        projected = l.combined + l.flow + nflow * (26 - round);
                        if (projected + 1 > bestLoc.getOrDefault(code, MIN_VALUE)) {
                            nstates.add(new ChoiceFant(l.location, fl.loc, nbits, nflow, l.combined + l.flow));
                            bestLoc.put(code, projected + 1);
                            if (projected > max) {
                                max = projected;
                            }
                        }
                    }

                    for (int dst : graph.get(l.location)) {
                        code = dst <= fl.loc ? (dst << 21) + (fl.loc << 15) : (fl.loc << 21) + (dst << 15);
                        code += fl.bitOps;
                        projected = l.combined + l.flow + fl.flow * (26 - round);
                        if (projected + 1 > bestLoc.getOrDefault(code, MIN_VALUE)) {
                            nstates.add(new ChoiceFant(dst, fl.loc, fl.bitOps, fl.flow, l.combined + l.flow));
                            bestLoc.put(code, projected + 1);
                            if (projected > max) {
                                max = projected;
                            }
                        }
                    }
                }
            }

            states = nstates;
        }

        return max;
    }
}
