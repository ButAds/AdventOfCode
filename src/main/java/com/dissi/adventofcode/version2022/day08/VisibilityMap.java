package com.dissi.adventofcode.version2022.day08;


import java.util.Arrays;
import java.util.List;

public class VisibilityMap {

    private final byte[][] trees;
    private final boolean[][] visibility;
    private final int width;

    public VisibilityMap(List<String> data) {
        // Hardcode width, square thank god.
        width = data.size();
        this.trees = new byte[data.size()][data.size()];
        this.visibility = new boolean[data.size()][data.size()];
        addData(data);
    }

    private void addData(List<String> input) {
        for (int row = 0; row < input.size(); row++) {
            char[] chars = input.get(row).toCharArray();
            for (int i = 0; i < chars.length; i++) {
                trees[row][i] = (byte) (chars[i] - '0');
            }
            checkVisibilityForRow(row);
        }
        for (int i = 0; i < width; i++) {
            checkVisibilityForColumn(i);
        }

    }


    public int countVisible() {
        int count = 0;
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < visibility[i].length; j++) {
                count += visibility[i][j] ? 1 : 0;
            }
        }
        return count;
    }

    private void checkVisibilityForRow(int row) {
        if (row == 0 || row == trees.length - 1) {
            Arrays.fill(visibility[row], false);
            return;
        }

        int leftMax = Integer.MIN_VALUE;
        for (int i = 0; i < width; i++) {
            if (trees[row][i] > leftMax) {
                visibility[row][i] = true;
                leftMax = trees[row][i];
            }
        }
        int rightMax = Integer.MIN_VALUE;
        for (int i = width - 1; i >= 0; i--) {
            if (trees[row][i] > rightMax) {
                visibility[row][i] = true;
                rightMax = trees[row][i];
            }
        }
    }

    private void checkVisibilityForColumn(int column) {
        if (column == 0 || column == width - 1) {
            for (int i = 0; i < trees.length; i++) {
                visibility[i][column] = true;
            }
            return;
        }
        int topMax = Integer.MIN_VALUE;
        for (int i = 0; i < trees.length; i++) {
            if (trees[i][column] > topMax) {
                visibility[i][column] = true;
                topMax = trees[i][column];
            }
        }
        int bottomMax = Integer.MIN_VALUE;
        for (int i = trees.length - 1; i >= 0; i--) {
            if (trees[i][column] > bottomMax) {
                visibility[i][column] = true;
                bottomMax = trees[i][column];
            }
        }
    }

    public int getMaxScenicScore() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < width; j++) {
                int score = getScenicScore(i, j);
                max = Integer.max(max, score);
            }
        }
        return max;
    }

    private int getScenicScore(int row, int column) {
        int top = getTopVisible(row, column);
        int bottom = getBottomVisible(row, column);
        int left = getLeftVisible(row, column);
        int right = getRightVisible(row, column);
        return top * bottom * left * right;
    }

    private int getTopVisible(int row, int column) {
        if (row == 0) {
            return 0;
        }
        int count = 0;
        int i = row - 1;
        while (i >= 0 && trees[i][column] < trees[row][column]) {
            count++;
            i--;
        }
        return count + (i >= 0 ? 1 : 0);
    }

    private int getBottomVisible(int row, int column) {
        if (row == trees.length - 1) {
            return 0;
        }
        int count = 0;
        int i = row + 1;
        while (i < trees.length && trees[i][column] < trees[row][column]) {
            count++;
            i++;
        }
        return count + (i < trees.length ? 1 : 0);
    }

    private int getLeftVisible(int row, int column) {
        if (column == 0) {
            return 0;
        }
        int count = 0;
        int i = column - 1;
        while (i >= 0 && trees[row][i] < trees[row][column]) {
            count++;
            i--;
        }
        return count + (i >= 0 ? 1 : 0);
    }

    private int getRightVisible(int row, int column) {
        if (column == width - 1) {
            return 0;
        }
        int count = 0;
        int i = column + 1;
        while (i < width && trees[row][i] < trees[row][column]) {
            count++;
            i++;
        }
        return count + (i < width ? 1 : 0);
    }
}
