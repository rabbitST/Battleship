package com.codecool.battleship;

//The Square class contains X and Y fields.
//        The Square class has a SquareStatus field.
//        The Square class has method that returns a graphical representation of SquareStatus.

import com.codecool.battleship.util.SquareStatus;

public class Square {
    private int x;
    private int y;
    SquareStatus squareStatus;

    public Square(int x, int y, SquareStatus squareStatus) {
        this.x = x;
        this.y = y;
        this.squareStatus = squareStatus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }

    public char getGraphicalRepresentationOfSquareStatus() {
        return squareStatus.getCharacter();
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                ", squareStatus=" + squareStatus +
                '}';
    }
}
