package com.dissi.adventofcode.version2024.day14;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Grid;
import com.dissi.adventofcode.helpers.Position;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class RestroomRedoubt {

    private boolean example = false;
    private List<String> data;
    private Grid<List<Robot>> robots;

    public RestroomRedoubt() {
        this.data = BufferUtils.getInputAsStringList(2024, 14, example);
    }

    private List<Robot> loadRobots() {
        List<Robot> robotList = new ArrayList<>();
        int maxX = example ? 11 : 101;
        int maxY = example ? 7 : 103;
        List<Robot>[][] dataGrid = new List[maxY][maxX];
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                dataGrid[y][x] = new ArrayList<>();
            }
        }
        this.robots = new Grid<>(dataGrid);

        for (String s : data) {
            String[] p = s.split(" ")[0].split("=")[1].split(",");
            String[] v = s.split(" ")[1].split("=")[1].split(",");

            Position initialPos = new Position(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
            Robot r = new Robot(initialPos, Integer.parseInt(v[0]), Integer.parseInt(v[1]), robots);
            robotList.add(r);
        }
        return robotList;
    }

    @SolutionAnnotation(year = 2024, day = 14, section = 1)
    public long doTheRobot() {
        List<Robot> list = loadRobots();
        for (int i = 0; i < 100; i++) {
            for (Robot r : list) {
                r.step();
            }
        }

        long qOne = 0;
        long qTwo = 0;
        long qThree = 0;
        long qFour = 0;

        for (Robot r : list) {
            if (r.pos.getX() == (this.robots.sizeX() / 2)) {
                continue;
            }
            if (r.pos.getY() == (this.robots.sizeY() / 2)) {
                continue;
            }
            // Left?
            if (r.pos.getX() < (this.robots.sizeX() / 2)) {
                // Top?
                if (r.pos.getY() < (this.robots.sizeY() / 2)) {
                    qOne++;
                } else {
                    qTwo++;
                }
            } else {
                // Top?
                if (r.pos.getY() < (this.robots.sizeY() / 2)) {
                    qThree++;
                } else {
                    qFour++;
                }
            }
        }
        return qOne * qTwo * qThree * qFour;
    }

    public void doImage(long step) {
        if (example) {
            return;
        }
        File toWriteTo = new File("images/" + step + ".png");
        if (toWriteTo.exists()) {
            return;
        }
        BufferedImage image = new BufferedImage(robots.sizeX(), robots.sizeY(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < robots.sizeX(); x++) {
            for (int y = 0; y < robots.sizeY(); y++) {
                if (!robots.at(new Position(x, y), null).isEmpty()) {
                    image.setRGB(x, y, 0xFFFFFFF);
                }
            }
        }

        try {
            ImageIO.write(image, "png", toWriteTo);
        } catch (IOException e) {
            // Ya ffed up
            e.printStackTrace();
        }
    }

    @SolutionAnnotation(year = 2024, day = 14, section = 2)
    public long doTheEasterEgg() {
        List<Robot> list = loadRobots();
        long steps = 0;

        do {

            for (Robot r : list) {
                r.step();
            }
            steps++;
            doImage(steps);

        } while (steps != 20000);
        // Check image folder and hope for finding an answer
        return steps;
    }


    static class Robot {

        private final Grid<List<Robot>> grid;
        private final int velocityX;
        private final int velocityY;
        private Position pos;

        public Robot(Position initialPos, int velocityX, int velocityY, Grid<List<Robot>> inGrid) {
            this.pos = initialPos;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.grid = inGrid;

            grid.at(pos, null).add(this);

        }

        public void step() {
            grid.at(pos, null).remove(this);
            pos = clamp(pos.add(velocityX, velocityY), grid.sizeX(), grid.sizeY());
            grid.at(pos, null).add(this);

        }

        private Position clamp(Position p, int clampX, int clampY) {
            long posX = p.getX();
            long posY = p.getY();
            if (posX < 0) {
                posX += clampX;
            } else if (posX >= clampX) {
                posX %= clampX;
            }
            if (posY < 0) {
                posY += clampY;
            } else if (posY >= clampY) {
                posY %= clampY;
            }
            return new Position(posX, posY);
        }
    }
}
