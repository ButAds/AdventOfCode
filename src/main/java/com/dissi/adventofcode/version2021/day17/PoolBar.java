package com.dissi.adventofcode.version2021.day17;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

public class PoolBar {

    private final int startX;
    private final int endX;
    private final int startY;
    private final int endY;
    @Getter
    private int maxY;
    @Getter
    private int hits = 0;

    public PoolBar(String shotType) {
        Pattern pattern = Pattern.compile("target area: x=(\\d*)..(\\d*), y=-(\\d*)..-(\\d*)");
        Matcher matcher = pattern.matcher(shotType);
        matcher.matches();
        this.startX = Math.min(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        this.endX = Math.max(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        this.startY = Math.max(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))) * -1;
        this.endY = Math.min(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))) * -1;
        this.maxY = calculateMaxY();
    }

    public void solve() {
        int upperBoundY = (-startY) - startY + 1;
        int lowerBoundX = (int) Math.sqrt(startX * 2);
        for (int x = lowerBoundX; x <= endX; x++) {
            for (int y = startY; y < upperBoundY; y++) {
                if (doSteps(x, y)) {
                    hits++;
                }
            }
        }
    }

    private int calculateMaxY() {
        int velocityY = (-startY) - 1;
        return (velocityY * (velocityY + 1)) / 2;
    }

    private boolean doSteps(int startVelX, int startVelY) {
        int currX = 0;
        int currY = 0;
        int currVelX = startVelX;
        int currVely = startVelY;
        while (true) {
            currX += currVelX;
            currY += currVely;

            if (currX >= startX && currX <= endX
                && currY >= startY && currY <= endY) {
                return true;
            }
            if (currVely < 0 && currY < startY) {
                break; // Already under
            }
            if (currVelX > 0 && currX > endX) {
                break; // already past it
            }
            if (currVelX == 0 && currX < startX && currX > endX) {
                break; // can no longer go left or right and am before/passed the point
            }

            // next step
            if (currVelX > 0) {
                currVelX -= 1;
            }

            currVely -= 1;
        }
        return false;
    }
}
