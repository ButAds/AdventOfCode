package com.dissi.adventofcode.version2021.day18;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SnailForm {

    public static int calculateMaximumMagnitude(List<String> lines) {
        var largestMagnitude = Integer.MIN_VALUE;
        for (var outer = 0; outer < lines.size(); outer++) {
            for (var inner = 0; inner < lines.size(); inner++) {
                if (outer == inner) {
                    continue;
                }
                List<Node> sumResult = performSum(parseLine(lines.get(outer)), parseLine(lines.get(inner)));
                reduceList(sumResult);

                var magnitude = magnitude(sumResult);
                if (magnitude > largestMagnitude) {
                    largestMagnitude = magnitude;
                }
            }
        }
        return largestMagnitude;
    }

    public static int calculateSum(List<String> lines) {
        List<Node> previous = parseLine(lines.get(0));
        for (int i = 1; i < lines.size(); i++) {
            previous = performSum(previous, parseLine(lines.get(i)));
            reduceList(previous);
        }
        return magnitude(previous);
    }

    private static boolean explode(List<Node> list) {
        for (var i = 0; i < list.size(); i++) {
            if (list.get(i).depth != 4) {
                continue;
            }
            var left = list.get(i);
            var newDepth = left.depth - 1;
            if (i != 0) {
                list.get(i - 1).value += left.value;
            }
            var right = list.get(i + 1);
            if (i < list.size() - 2) {
                list.get(i + 2).value += right.value;
            }
            list.remove(i + 1);
            list.remove(i);
            list.add(i, new Node(newDepth));
            return true;
        }
        return false;
    }

    private static boolean split(List<Node> list) {
        for (var i = 0; i < list.size(); i++) {
            if (list.get(i).value > 9) {
                var nodeToSplit = list.get(i);
                var splitDepth = nodeToSplit.depth + 1;
                var splitValue = (float) nodeToSplit.value;
                list.remove(i);
                list.add(i, new Node(splitDepth, (int) Math.floor(splitValue / 2)));
                list.add(i + 1, new Node(splitDepth, (int) Math.ceil(splitValue / 2)));
                return true;
            }
        }
        return false;
    }

    private static int magnitude(List<Node> list) {
        for (var depth = 3; depth >= 0; depth--) {
            var reduced = false;
            do {
                reduced = false;
                for (var j = 0; j < list.size() - 1; j++) {
                    var left = list.get(j);
                    var right = list.get(j + 1);
                    if (left.depth != depth) {
                        continue;
                    }
                    list.remove(j + 1);
                    list.remove(j);
                    list.add(j, new Node(depth - 1, 3 * left.value + 2 * right.value));
                    reduced = true;
                    break;
                }
            } while (reduced);
        }
        return list.get(0).value;
    }

    private static List<Node> parseLine(String line) {
        var list = new ArrayList<Node>();
        var depth = -1;
        for (var cc : line.toCharArray()) {
            switch (cc) {
                case '[':
                    depth++;
                    break;
                case ']':
                    depth--;
                    break;
                case ',':
                    break;
                default:
                    var value = Integer.parseInt(String.valueOf(cc));
                    var node = new Node();
                    node.depth = depth;
                    node.value = value;
                    list.add(node);
                    break;
            }
        }
        return list;
    }

    private static List<Node> performSum(List<Node> operandA, List<Node> operandB) {
        if (operandA.isEmpty()) {
            return operandB;
        }
        operandA.addAll(operandB);
        operandA = operandA.stream().peek(e -> e.depth++).collect(Collectors.toList());
        return operandA;
    }

    private static void reduceList(List<Node> nodeList) {
        var reduced = false;
        do {
            reduced = explode(nodeList);
            if (reduced) {
                continue;
            }
            reduced = split(nodeList);
        } while (reduced);
    }

    private static class Node {

        private int value = 0;
        private int depth;

        public Node() {
        }

        public Node(int depth) {
            this.depth = depth;
        }

        public Node(int depth, int value) {
            this.depth = depth;
            this.value = value;
        }
    }
}
