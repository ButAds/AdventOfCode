package com.dissi.adventofcode.version2024.day07;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgeRepair {

    private final List<String> data;

    public BridgeRepair() {
        this.data = BufferUtils.getInputAsStringList(2024, 7, false);
    }

    @SolutionAnnotation(day = 7, section = 1, year = 2024)
    public long ropeBridge() {
        return operatorTrouble(data, new Operator[] {Operator.MULTIPLY, Operator.PLUS});
    }

    @SolutionAnnotation(day = 7, section = 2, year = 2024)
    public long ropeBridgeOopsAlsoMoreOperators() {
        return operatorTrouble(data, new Operator[] {Operator.MULTIPLY, Operator.PLUS, Operator.COMBINE});
    }


    long operatorTrouble(List<String> lines, Operator[] operators) {
        long result = 0L;

        for (String line : lines) {
            String[] split = line.split(":");
            long resultValue = Long.parseLong(split[0].trim());
            List<Long> values = Arrays.stream(split[1].trim().split(" ")).map(Long::parseLong).toList();

            List<List<Operator>> operatorCombinations = generateCombis(operators, values.size() - 1);

            for (List<Operator> combination : operatorCombinations) {
                Long solution = values.get(0);

                for (int j = 0; j < combination.size(); j++) {
                    Operator op = combination.get(j);
                    Long number = values.get(j + 1);

                    if (op == Operator.PLUS) {
                        solution += number;
                    } else if (op == Operator.MULTIPLY) {
                        solution *= number;
                    } else {
                        solution = Long.parseLong(solution.toString() + number.toString());
                    }

                    if (solution > resultValue) {
                        break;
                    }
                }

                if (solution.equals(resultValue)) {
                    result += resultValue;
                    break;
                }
            }
        }

        return result;
    }

    private List<List<Operator>> generateCombis(Operator[] operators, int length) {
        List<List<Operator>> result = new ArrayList<>();

        result.add(new ArrayList<>());

        for (int i = 0; i < length; i++) {
            List<List<Operator>> newCombinations = new ArrayList<>();
            for (List<Operator> combination : result) {
                for (Operator operator : operators) {
                    List<Operator> newCombination = new ArrayList<>(combination);
                    newCombination.add(operator);
                    newCombinations.add(newCombination);
                }
            }
            result = newCombinations;
        }

        return result;
    }


    enum Operator {
        PLUS,
        MULTIPLY,
        COMBINE
    }

}
