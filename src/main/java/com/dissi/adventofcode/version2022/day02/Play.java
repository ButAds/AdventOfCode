package com.dissi.adventofcode.version2022.day02;

public record Play(int player, int opponent) {

    static Play create(String data) {
        // Use "Char" magic to calculate worth-ness of moves
        return new Play(data.charAt(0) - 'A', data.charAt(2) - 'X');
    }

    int pick() {
        return (player + opponent + 2) % 3;
    }

    int moveResult() {
        return (pick() - player + 4) % 3;
    }
    int movePerfectResult() {
        return (opponent - player + 4) % 3;
    }
}
