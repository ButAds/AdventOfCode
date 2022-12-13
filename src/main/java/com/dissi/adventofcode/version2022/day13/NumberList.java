package com.dissi.adventofcode.version2022.day13;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class NumberList implements Comparable<NumberList> {

    public ArrayList<NumberList> sublists = new ArrayList<>();
    private int value = -1;


    public static @NotNull NumberList parse(@NotNull String s) {
        NumberList numbers = new NumberList();
        int index = 1;
        while (index < s.length()) {
            if (s.charAt(index) == '[') {
                int depth = 1;
                int endIndex = index + 1;
                while (depth > 0) {
                    if (s.charAt(endIndex) == ']') {
                        depth--;
                    } else if (s.charAt(endIndex) == '[') {
                        depth++;
                    }
                    endIndex++;
                }
                numbers.sublists.add(parse(s.substring(index, endIndex)));
                index = endIndex;
            } else {
                int endIndex = s.indexOf(",", index + 1);
                if (endIndex == -1) {
                    endIndex = s.indexOf("]", index);
                }
                String num = s.substring(index, endIndex);
                NumberList number = new NumberList();
                if (num.length() != 0) {
                    number.value = Integer.parseInt(num);
                    numbers.sublists.add(number);
                }
                index = endIndex;
            }
            index++;
        }
        return numbers;
    }

    @Override
    public int compareTo(@NotNull NumberList other) {
        int compareIndex = 0;
        while (compareIndex < this.sublists.size() || compareIndex < other.sublists.size()) {
            if (this.sublists.size() <= compareIndex) {
                return 1;
            }
            if (other.sublists.size() <= compareIndex) {
                return -1;
            }

            NumberList leftList = this.sublists.get(compareIndex);
            NumberList rightList = other.sublists.get(compareIndex);

            if (!leftList.sublists.isEmpty() && rightList.value != -1) {
                NumberList testWith = new NumberList();
                NumberList sub = new NumberList();
                sub.value = rightList.value;
                testWith.sublists.add(sub);
                int compare = leftList.compareTo(testWith);
                if (compare != 0) {
                    return compare;
                } else {
                    compareIndex++;
                    continue;
                }
            }

            if (leftList.value != -1 && !rightList.sublists.isEmpty()) {
                NumberList testWith = new NumberList();
                NumberList sub = new NumberList();
                sub.value = leftList.value;
                testWith.sublists.add(sub);
                int compare = testWith.compareTo(rightList);
                if (compare != 0) {
                    return compare;
                } else {
                    compareIndex++;
                    continue;
                }
            }

            int leftVal = this.sublists.get(compareIndex).value;
            int rightVal = other.sublists.get(compareIndex).value;

            if (leftVal == -1 && rightVal == -1) {
                //only care about definitive result
                int compare = this.sublists.get(compareIndex).compareTo(other.sublists.get(compareIndex));
                if (compare != 0) {
                    return compare;
                } else {
                    compareIndex++;
                    continue;
                }
            }

            //finally, simple integer comparison
            if (leftVal < rightVal) {
                return 1;
            }
            if (leftVal > rightVal) {
                return -1;
            }

            //if equal, move onto next index of loop
            compareIndex++;
        }
        //if nothing returned yet, test was inconclusive
        return 0;
    }
}
