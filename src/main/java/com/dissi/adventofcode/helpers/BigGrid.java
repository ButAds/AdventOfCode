package com.dissi.adventofcode.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigGrid<T> {

    final Map<Position, T> grid;

    public BigGrid(T[][] g) {
        this.grid = new HashMap<>();
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                grid.put(new Position(j, i), g[i][j]);
            }
        }
    }

    public BigGrid(Map<Position, T> grid) {
        this.grid = grid;
    }

    public long minY() {
        return grid.keySet().stream().mapToLong(Position::getY).min().orElse(0);
    }

    public long maxY() {
        return grid.keySet().stream().mapToLong(Position::getY).max().orElse(0);
    }

    public long minX() {
        return grid.keySet().stream().mapToLong(Position::getX).min().orElse(0);
    }

    public long maxX() {
        return grid.keySet().stream().mapToLong(Position::getX).max().orElse(0);
    }

    public long height() {
        return maxY() + 1 - minY();
    }

    public long width() {
        return maxX() + 1 - minX();
    }

    public void place(BigGrid<T> s) {
        this.grid.putAll(s.grid);
    }

    public Set<Position> positions() {
        return grid.keySet();
    }

    public BigGrid<T> move(long x, long y) {
        return new BigGrid<>(
            grid.entrySet().stream()
                .map(e -> new Pair<>(e.getKey().add(x, y), e.getValue()))
                .collect(Collectors.toMap(Pair::a, Pair::b)));
    }

    public boolean canPlace(BigGrid<T> g, long r, long c) {
        return g.grid.keySet().stream()
            .map(l -> l.add(c, r))
            .noneMatch(grid::containsKey);
    }

    public boolean canPlace(BigGrid<T> g) {
        return g.grid.keySet().stream().noneMatch(grid::containsKey);
    }

    public void add(Position position, T s) {
        this.grid.put(position, s);
    }

    public boolean has(Position pos) {
        return this.grid.containsKey(pos);
    }

    public T getOrDefault(Position pos, T def) {
        return this.grid.getOrDefault(pos, def);
    }

    public Stream<Entry<Position, T>> streamPositions() {
        return this.grid.entrySet().stream();
    }

}
