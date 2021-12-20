package com.dissi.adventofcode.helpers;

public class Grid<T> {

    private T[][] grid;

    public Grid(T[][] grid) {
        this.grid = grid;
    }

    public void set(Position e, T c) {
        grid[e.getY()][e.getX()] = c;
    }

    public int sizeX() {
        return grid[0].length;
    }

    public int sizeY() {
        return grid.length;
    }

    public T at(Position p, T defaultResult) {
        if (p.getX() >= 0 && p.getX() < grid[0].length &&
            p.getY() >= 0 && p.getY() < grid.length &&
            grid[p.getY()][p.getX()] != null) {
            return grid[p.getY()][p.getX()];
        }

        return defaultResult;
    }

    @Override
    public String toString() {
        return "Grid{grid=xSize: " + sizeX() + ", ySize: " + sizeY() + '}';
    }
}
