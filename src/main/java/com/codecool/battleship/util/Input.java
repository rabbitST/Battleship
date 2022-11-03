package com.codecool.battleship.util;

import java.util.Random;

import static com.codecool.battleship.util.Constant.*;

public class Input {

    public Input() {
    }

    public boolean isValidCoordinate(String input) {
        if(input.length()<2) return false;
        String X = input.substring(0, 1);
        int XintValue = X.toUpperCase().charAt(0);
        if (XintValue < 'A' || XintValue > 'A' + BOARD_SIZE - 1) return false;
        String Y = input.substring(1);
        return Y.chars().allMatch(Character::isDigit) && Integer.parseInt(Y) >= 1 && Integer.parseInt(Y) <= BOARD_SIZE;
    }

    public int[] inputToArrayIndex(String input){
        return new int[]{Integer.parseInt(input.substring(1))-1,input.substring(0,1).toUpperCase().charAt(0)-65};
    }
    //    public Square turnInputIntoSquare(String coordinate, SquareStatus status){
//        int x =(coordinate.substring(0,1).toUpperCase().charAt(0)-CHARACTER_TO_BOARD_CORRECTION)*FIELD_SIZE_IN_PIXEL;
//        int y = Integer.parseInt(coordinate.substring(1))*FIELD_SIZE_IN_PIXEL;
//        return new Square(y,x,status);
//    }
    public int[] inputToPositionInPixel(String coordinate) {
        int x = (coordinate.substring(0, 1).toUpperCase().charAt(0) - CHARACTER_TO_BOARD_CORRECTION) * FIELD_SIZE_IN_PIXEL;
        int y = Integer.parseInt(coordinate.substring(1)) * FIELD_SIZE_IN_PIXEL;
        return new int[]{x, y};
    }

    public int moveToPositionInPixel(int coordinate) {
        return (coordinate + ARRAY_START_FROM_ZERO_CORRECTION) * FIELD_SIZE_IN_PIXEL;
    }

    public int createRandom(int bound){
        Random random= new Random();
        return random.nextInt(bound);
    }
}
