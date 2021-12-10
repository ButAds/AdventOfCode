package com.dissi.adventofcode.version2021.day10;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Chunk {

    @Getter
    private final Character character;

    public Chunk(Character c) {
        this.character = c;
    }

    public static Chunk getMatchedClose(Chunk closingStatement) {
        return switch (closingStatement.character) {
            case '}' -> new Chunk('{');
            case ']' -> new Chunk('[');
            case '>' -> new Chunk('<');
            case ')' -> new Chunk('(');
            default -> null;
        };
    }

    public boolean isCloseSection() {
        return switch (character) {
            case '}', ']', '>', ')' -> true;
            default -> false;
        };
    }

    public boolean isAdd() {
        return switch (character) {
            case '{', '[', '<', '(' -> true;
            default -> false;
        };
    }

    public int getScore() {
        return switch (character) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> 0;
        };
    }

    public int getOpenScore() {
        return switch (character) {
            case '(' -> 1;
            case '[' -> 2;
            case '{' -> 3;
            case '<' -> 4;
            default -> 0;
        };
    }
}
