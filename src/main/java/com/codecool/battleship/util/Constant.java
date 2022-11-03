package com.codecool.battleship.util;

import java.awt.*;
import java.util.Arrays;

public class Constant {
    public static final int GUI_PLAYER_INPUT_SWITCH = 400;
    public static final int ARRAY_START_FROM_ZERO_CORRECTION = 1;
    public static final int FIELD_SIZE_IN_PIXEL = 60;
    public static final int CHARACTER_TO_BOARD_CORRECTION = 64;
    public static final int BOARD_SIZE = 10;
    public static final String DATA_FILE = "src/main/java/com/codecool/battleship/data/highScore.csv";
    public static final Color BOARD_COLOR = Color.CYAN;
    public static final int TABLE_DIMENSIONS=10;
    public static final int[][] HORIZONTAL_AMBIENT_MATRIX={{1, -1}, {0, -1}, {-1, -1},
            {-1, 0}, {+1, 0},
            {-1, 1}, {0, +1}, {+1, +1}};
    public static final int[][] VERTICAL_AMBIENT_MATRIX={{-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, +1},
            {+1, -1}, {1, 0}, {+1, +1}};
}
