package com.dissi.adventofcode.version2022.day22.day21;

import static java.util.stream.Collectors.toList;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Cubeinator {

    private static final Direction[] DIRECTIONS;

    static {
        DIRECTIONS = new Direction[] {Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH};
    }

    private final List<String> data;
    private char[][] board;
    private int direction;
    private Position currentPosition;
    private List<Integer> steps;
    private List<String> turns;

    public Cubeinator() {
        this.data = BufferUtils.getInputAsStringList(2022, 22, false);
    }

    @SolutionAnnotation(day = 22, section = 1, year = 2022)
    public long mapinator() {
        init();
        runPath(true);
        return password();
    }

    @SolutionAnnotation(day = 22, section = 2, year = 2022)
    public long cubiWalk() {
        init();
        runPath(false);
        return password();
    }

    private long password() {
        return 1000 * (currentPosition.getY() + 1) + 4 * (currentPosition.getX() + 1) + direction;
    }

    private void init() {
        board = new char[data.size() - 1][];
        String path = data.get(data.size() - 1);
        for (int i = 0; i < data.size() - 1; i++) {
            board[i] = data.get(i).toCharArray();
        }

        steps = Arrays.stream(path.replace("L", "R").split("R")).mapToInt(Integer::parseInt).boxed()
            .collect(toList());
        turns = new ArrayList<>(Arrays.asList(path.replaceAll("\\d+", " ").trim().split(" ")));
        currentPosition = new Position(0, 0);

        while (board[(int) currentPosition.getY()][(int) currentPosition.getX()] != '.') {
            currentPosition = currentPosition.add(1, 0);
        }
    }

    private void runPath(boolean doOne) {
        move(steps.remove(0), doOne);
        while (!steps.isEmpty()) {
            direction = (direction + (turns.remove(0).equals("R") ? 1 : 3)) % 4;
            move(steps.remove(0), doOne);
        }
    }

    private void move(int step, boolean doOne) {
        Position next = currentPosition;
        while (step > 0) {
            next = DIRECTIONS[direction].translate(next);
            if (doOne) {
                next = boundWalk(next);
            } else {
                next = cubeWalk(next);
            }
            if (board[(int) next.getY()].length <= next.getX()) {
                continue;
            }
            if (board[(int) next.getY()][(int) next.getX()] == '.') {
                step--;
                currentPosition = next;
            } else if (board[(int) next.getY()][(int) next.getX()] == '#') {
                step = 0;
            }
        }
    }

    private Position boundWalk(Position p) {
        if (p.getX() < 0) {
            return new Position(board[0].length - 1L, p.getY());
        } else if (p.getX() >= board[0].length) {
            return new Position(0, p.getY());
        } else if (p.getY() < 0) {
            return new Position(p.getX(), board.length - 1L);
        } else if (p.getY() >= board.length) {
            return new Position(p.getX(), 0);
        }
        return p;
    }

    private Position cubeWalk(Position p) {
        return switch (direction) {
            case 0 -> cubeWalkRight(p);
            case 1 -> cubeWalkDown(p);
            case 2 -> cubeWalkLeft(p);
            case 3 -> cubeWalkUp(p);
            default -> cubeWalkUp(p);
        };
    }

    private Position cubeWalkRight(Position p) {
        Position np;
        if (p.getY() < 50) {
            if (p.getX() < 150) {
                return p;
            } else {
                np = new Position(99, 149 - p.getY());
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 2;
                }
                return np;
            }
        } else if (p.getY() < 100) {
            if (p.getX() < 100) {
                return p;
            } else {
                np = new Position(p.getY() + 50, 49);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 3;
                }
                return np;
            }
        } else if (p.getY() < 150) {
            if (p.getX() < 100) {
                return p;
            } else {
                np = new Position(149, 149 - p.getY());
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 2;
                }
                return np;
            }
        } else {
            if (p.getX() < 50) {
                return p;
            } else {
                np = new Position(p.getY() - 100, 149);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 3;
                }
                return np;
            }
        }
    }

    private Position cubeWalkDown(Position p) {
        Position np;
        if (p.getX() < 50) {
            if (p.getY() < 200) {
                return p;
            } else {
                return new Position(p.getX() + 100, 0);
            }
        } else if (p.getX() < 100) {
            if (p.getY() < 150) {
                return p;
            } else {
                np = new Position(49, p.getX() + 100);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 2;
                }
                return np;
            }
        } else {
            if (p.getY() < 50) {
                return p;
            } else {
                np = new Position(99, p.getX() - 50);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 2;
                }
                return np;
            }
        }
    }

    private Position cubeWalkLeft(Position p) {
        Position np;
        if (p.getY() < 50) {
            if (p.getX() >= 50) {
                return p;
            } else {
                np = new Position(0, 149 - p.getY());
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 0;
                }
                return np;
            }
        } else if (p.getY() < 100) {
            if (p.getX() >= 50) {
                return p;
            } else {
                np = new Position(p.getY() - 50, 100);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 1;
                }
                return np;
            }
        } else if (p.getY() < 150) {
            if (p.getX() >= 0) {
                return p;
            } else {
                np = new Position(50, 149 - p.getY());
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 0;
                }
                return np;
            }
        } else {
            if (p.getX() >= 0) {
                return p;
            } else {
                np = new Position(p.getY() - 100, 0);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 1;
                }
                return np;
            }
        }
    }

    private Position cubeWalkUp(Position p) {
        Position np;
        if (p.getX() < 50) {
            if (p.getY() >= 100) {
                return p;
            } else {
                np = new Position(50, p.getX() + 50);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 0;
                }
                return np;
            }
        } else if (p.getX() < 100) {
            if (p.getY() >= 0) {
                return p;
            } else {
                np = new Position(0, p.getX() + 100);
                if (board[(int) np.getY()][(int) np.getX()] != '#') {
                    direction = 0;
                }
                return np;
            }
        } else {
            if (p.getY() >= 0) {
                return p;
            } else {
                return new Position(p.getX() - 100, 199);
            }
        }
    }

}
