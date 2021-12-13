package com.dissi.adventofcode.version2020.day03;

import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Toboggan {

    public static long traverse(List<String> treeList, int diffY, int diffX) {
        long trees = 0;
        int currX = 0;
        int currY = 0;
        while (currY < treeList.size()) {
            String treeLine = treeList.get(currY);
            char treeChar = treeLine.charAt(currX);
            if (treeChar == '#') {
                trees++;
            }

            currY += diffY;
            currX = (currX + diffX) % treeLine.length();
        }
        return trees;
    }
}
