package com.dissi.adventofcode.version2022.day19;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mine {

    private final List<BluePrint> blueprints;

    public Mine() {
        this.blueprints = BufferUtils.getInputAsStringList(2022, 19, false).stream().map(s -> {
            String[] line = s.split(" ");
            return new BluePrint(Integer.parseInt(line[6]), Integer.parseInt(line[12]), Integer.parseInt(line[18]),
                Integer.parseInt(line[27]), Integer.parseInt(line[21]), Integer.parseInt(line[30]));
        }).toList();

    }

    @SolutionAnnotation(day = 19, section = 1, year = 2022)
    public long bluePrint() {
        return getMaximumGeodes(24, blueprints, true);
    }

    @SolutionAnnotation(day = 19, section = 2, year = 2022)
    public long doTwo() {
        return getMaximumGeodes(32, blueprints.subList(0, 3), false);
    }

    public long getMaximumGeodes(int minutes, List<BluePrint> toCalculateFor, boolean sumResult) {

        IntStream solutionStream = IntStream.range(0, toCalculateFor.size()).parallel().map(i -> {
            int result = toCalculateFor.get(i)
                .calculate(minutes, new int[] {1, 0, 0, 0}, new int[4], new HashMap<>(), 0);
            if (sumResult) {
                return (i + 1) * result;
            }
            return result;
        });
        if (sumResult) {
            return solutionStream.sum();
        } else {
            return solutionStream.reduce(1, (left, right) -> left * right);
        }
    }

    static class BluePrint {

        private static final int ORE = 0;
        private static final int CLAY = 1;
        private static final int OBSIDIAN = 2;
        private static final int GEODE = 3;

        private final int[] oreRequired = new int[4];
        private final int[] clayRequired = new int[4];
        private final int[] obsidianRequired = new int[4];
        private final int[] geodeRequired = new int[4];

        public BluePrint(int oreCost, int clayCost, int obsidianCost, int geodeCost, int clayCostObsidian,
            int geodeObsidianCost) {
            oreRequired[ORE] = oreCost;
            clayRequired[ORE] = clayCost;
            obsidianRequired[ORE] = obsidianCost;
            obsidianRequired[CLAY] = clayCostObsidian;
            geodeRequired[ORE] = geodeCost;
            geodeRequired[OBSIDIAN] = geodeObsidianCost;
        }

        public int calculate(int timeRemaining, int[] possibleRobots, int[] currentResources,
            Map<String, Integer> seen, int currentMax) {
            String uniqueKey =
                timeRemaining
                    + "-"
                    + Arrays.stream(possibleRobots).mapToObj(String::valueOf).collect(Collectors.joining("-"))
                    + "-"
                    + Arrays.stream(currentResources).mapToObj(String::valueOf).collect(Collectors.joining("-"));

            if (seen.containsKey(uniqueKey)) {
                return seen.get(uniqueKey);
            }

            if (timeRemaining == 0) {
                return currentResources[GEODE];
            }

            if (currentMax > currentResources[GEODE] + (timeRemaining * (possibleRobots[GEODE] + timeRemaining))) {
                return -1;
            }

            List<int[]> robots = new ArrayList<>(4);
            List<int[]> possibleResources = new ArrayList<>(4);

            createPossibilities(timeRemaining, robots, possibleRobots, currentResources, possibleResources);

            int answer = 0;
            for (int i = 0; i < robots.size(); i++) {
                int[] resources = new int[possibleRobots.length];
                for (int j = 0; j < currentResources.length; j++) {
                    resources[j] += possibleRobots[j] + possibleResources.get(i)[j];
                }
                //Next minute
                answer = Math.max(answer, calculate(timeRemaining - 1, robots.get(i), resources, seen, currentMax));

                if (answer > currentMax) {
                    currentMax = answer;
                }
            }
            seen.put(uniqueKey, answer);
            return answer;
        }

        private void createPossibilities(int timeRemaining, List<int[]> robots, int[] possibleRobots,
            int[] currentResources, List<int[]> possibleResources) {
            boolean geodePossible = possible(currentResources, geodeRequired);
            if (geodePossible) {
                possibleResources.add(subtract(currentResources, geodeRequired));
                robots.add(addRobotAndClone(possibleRobots, GEODE));
                return;
            }

            boolean obsidianPossible = possible(currentResources, obsidianRequired);
            boolean clayPossible = possible(currentResources, clayRequired);
            boolean orePossible = possible(currentResources, oreRequired);
            if (obsidianPossible && (currentResources[OBSIDIAN] <= timeRemaining * (geodeRequired[OBSIDIAN]
                - possibleRobots[OBSIDIAN]))) {
                possibleResources.add(subtract(currentResources, obsidianRequired));
                robots.add(addRobotAndClone(possibleRobots, OBSIDIAN));
            }
            if (clayPossible && (currentResources[CLAY] <= timeRemaining * (obsidianRequired[CLAY]
                - possibleRobots[CLAY]))) {
                possibleResources.add(subtract(currentResources, clayRequired));
                robots.add(addRobotAndClone(possibleRobots, CLAY));
            }
            if (orePossible) {
                int calcMax = Math.max(Math.max(oreRequired[ORE], clayRequired[ORE]),
                    Math.max(obsidianRequired[ORE], geodeRequired[ORE]));
                if ((currentResources[0] <= timeRemaining * (calcMax - possibleRobots[ORE]))) {
                    possibleResources.add(subtract(currentResources, oreRequired));
                    robots.add(addRobotAndClone(possibleRobots, ORE));
                }
            }
            possibleResources.add(currentResources);
            robots.add(possibleRobots);

        }

        public int[] addRobotAndClone(int[] current, int toAddWhere) {
            int[] addArray = Arrays.copyOf(current, current.length);
            addArray[toAddWhere] += 1;
            return addArray;
        }


        public int[] subtract(int[] currRes, int[] required) {
            int[] newRes = new int[currRes.length];
            for (int i = 0; i < currRes.length; i++) {
                newRes[i] = currRes[i] - required[i];
            }
            return newRes;
        }

        public boolean possible(int[] currRes, int[] required) {
            for (int i = 0; i < required.length; i++) {
                if (currRes[i] < required[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
