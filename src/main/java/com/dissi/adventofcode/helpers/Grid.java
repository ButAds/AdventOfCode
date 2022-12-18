package com.dissi.adventofcode.helpers;

import lombok.Getter;

public class Grid<T> {

    @Getter
    private final T[][] memGrid;

    public Grid(T[][] grid) {
        this.memGrid = grid;
    }

    public void set(Position e, T c) {
        memGrid[(int)e.getY()][(int)e.getX()] = c;
    }

    public int sizeX() {
        return memGrid[0].length;
    }

    public int sizeY() {
        return memGrid.length;
    }

    public T at(Position p, T defaultResult) {
        if (p.getX() >= 0 && p.getX() < memGrid[0].length &&
            p.getY() >= 0 && p.getY() < memGrid.length &&
            memGrid[(int)p.getY()][(int)p.getX()] != null) {
            return memGrid[(int)p.getY()][(int)p.getX()];
        }

        return defaultResult;
    }

    @Override
    public String toString() {
        return "Grid{grid=xSize: " + sizeX() + ", ySize: " + sizeY() + '}';
    }
}
