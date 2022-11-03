package com.codecool.battleship;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[][] leftSquares = {{1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}};
        System.out.println(Arrays.deepToString(leftSquares));
    }
}