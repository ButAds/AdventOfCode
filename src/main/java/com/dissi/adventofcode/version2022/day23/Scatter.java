package com.dissi.adventofcode.version2022.day23;

import static com.dissi.adventofcode.helpers.Direction.EAST;
import static com.dissi.adventofcode.helpers.Direction.NORTH;
import static com.dissi.adventofcode.helpers.Direction.NORTHEAST;
import static com.dissi.adventofcode.helpers.Direction.NORTHWEST;
import static com.dissi.adventofcode.helpers.Direction.SOUTH;
import static com.dissi.adventofcode.helpers.Direction.SOUTHEAST;
import static com.dissi.adventofcode.helpers.Direction.SOUTHWEST;
import static com.dissi.adventofcode.helpers.Direction.WEST;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Scatter {

    private final List<String> data;
    private Map<Position, List<Position>> proposals;
    private LinkedList<Direction> directions;
    private Set<Position> elves;
    private static final EnumMap<Direction, Direction[]> TO_CHECK;

    static {
        TO_CHECK = new EnumMap<>(Direction.class);
        TO_CHECK.put(NORTH, new Direction[] {NORTHWEST, NORTH, NORTHEAST});
        TO_CHECK.put(SOUTH, new Direction[] {SOUTHWEST, SOUTH, SOUTHEAST});
        TO_CHECK.put(WEST, new Direction[] {NORTHWEST, WEST, SOUTHWEST});
        TO_CHECK.put(EAST, new Direction[] {NORTHEAST, EAST, SOUTHEAST});
    }

    public Scatter() {
        this.data = BufferUtils.getInputAsStringList(2022, 23, false);
    }

    private void parse() {
        this.elves = new HashSet<>();
        this.directions = new LinkedList<>(Arrays.asList(NORTH, SOUTH, EAST, WEST));
        for (int x = 0; x < data.size(); x++) {
            String line = data.get(x);
            for (int y = 0; y < line.length(); y++) {
                if (line.charAt(y) == '#') {
                    elves.add(new Position(x, y));
                }
            }
        }
    }

    @SolutionAnnotation(day = 23, section = 1, year = 2022)
    public long runAroundScreaming() {
        parse();
        for (int i = 0; i < 10; i++) {
            playRound();
        }

        return findArea();
    }


    @SolutionAnnotation(day = 23, section = 2, year = 2022)
    public long releaseTheElves() {
        // @formatter:off
        //                  :NOMMMMMMMMMM$MR,
        //               DM+..................$MMMMMNOI
        //            ,M+...................?$8+.+D??DNZ?NM+
        //           M.......................==....=D???????$D
        //         N?............................$D=.DII77II??I8
        //        M=.............................7DD.=Z?????+ZN?+
        //       +...................................N?????????NM
        //       M.........................=O?......O8OI????????OO
        //      7..........................$NN=....8????I8??????IO
        //      M.................................?8??????N?????N:
        //      M.................................N?????ID7?7N8?N
        //     ,D.................................N?????N.ON=.=M
        //      M............................7N=..N??????8ND=.7
        //      O........................?Z8:,D...N??????7?.7.M
        //       M=................?ONZNN:    N...D7?????$?..M
        //        D.$NND88888D8OD?:   MNN  ,?+....D?????77N?
        //        M, MM  Z......D:,        ,7?......77??M
        //         7,  ,Z?........=8      ?D=.........N:
        //         D=8NN...........O +__===7..........8
        //         ZN+.............`_____`.....=.......M
        //        =7......$O+................+ND.......+,
        //       N......+...D..............N?,N=........M
        //      M...........D...........=N=,==...........M   $MMMN,
        //     N..........ID=..........N:,NNN.............ZM$.M+.INN7
        //    N......ZDI=...........+D.MMMNM8MZ...........8=O=.....O8M,
        //    ....$8  8............NNM,       78D........N............D
        //    ...M   8..........+NMN         :...N.......7..........+NNM
        //    =M7    M.......$NMI            M....N?....7=.........O..=
        //             :.~~~~               M.....O=..??.......88.....M
        //                                   ?.....N..$........O.......M
        //                                  M......D.D=.......?=.......D:
        //                                 MN......DO........$D........M
        //                                M.D.....=N........$?.........M
        //                               M.=D....II........8=.O........I
        //                              M..?7...O+........O..?D.......N
        //                             N...?7.7Z=........D=..N........8
        //                             I...?Z8..........$...D.......=M
        //                            M...O7..........=Z...N=.......M
        //                           =+.NO=..........ID..=8.........+
        //                           N..............8IO.=$.........M
        //
        //
        // @formatter:on
        parse();
        int rounds = 0;
        while (playRound()) {
            rounds++;
        }
        return rounds;
    }

    private boolean playRound() {
        proposals = new HashMap<>();
        doProposals();
        doMove();
        // You spin me right round...
        directions.add(directions.remove());
        return !proposals.isEmpty();
    }

    private void doProposals() {
        for (Position elf : elves) {
            findDirection(elf);
        }
    }

    private void findDirection(Position elf) {
        if (!hasNeighbor(elf)) {
            return;
        }
        for (Direction direction : directions) {
            boolean found = false;
            for (Direction p : TO_CHECK.get(direction)) {
                if (elves.contains(p.translate(elf))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Position next = TO_CHECK.get(direction)[1].translate(elf);
                proposals.computeIfAbsent(next, s -> new ArrayList<>());
                proposals.get(next).add(elf);
                return;
            }
        }
    }

    private boolean hasNeighbor(Position elf) {
        for (long i = elf.getX() - 1; i <= elf.getX() + 1; i++) {
            for (long j = elf.getY() - 1; j <= elf.getY() + 1; j++) {
                if (i == elf.getX() && j == elf.getY()) {
                    continue;
                }
                if (elves.contains(new Position(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void doMove() {
        for (Entry<Position, List<Position>> proposal : proposals.entrySet()) {
            if (proposal.getValue().size() == 1) {
                elves.remove(proposal.getValue().get(0));
                elves.add(proposal.getKey());
            }
        }
    }

    private long findArea() {
        long minX = Integer.MAX_VALUE;
        long minY = Integer.MAX_VALUE;

        long maxX = Integer.MIN_VALUE;
        long maxY = Integer.MIN_VALUE;
        for (Position elf : elves) {
            minX = Math.min(minX, elf.getX());
            minY = Math.min(minY, elf.getY());
            maxX = Math.max(maxX, elf.getX());
            maxY = Math.max(maxY, elf.getY());
        }
        return (maxX - minX + 1) * (maxY - minY + 1) - elves.size();
    }
}
