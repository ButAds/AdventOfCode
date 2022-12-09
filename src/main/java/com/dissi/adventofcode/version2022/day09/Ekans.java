package com.dissi.adventofcode.version2022.day09;

import static java.lang.Integer.signum;

import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Ekans {

    public static Position slither(Position head, Direction direction) {
        return direction.translate(head);
    }

    public static Position keepUpTail(Position head, Position tail) {
        Position delta = head.substract(tail);
        int dx = delta.getX();
        int dy = delta.getY();
        if (Math.abs(dy) == 2 || Math.abs(dx) == 2) {
            return tail.add(signum(dx), signum(dy));
        }
        return tail;
    }
}
