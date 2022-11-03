package com.codecool.battleship;

import com.codecool.battleship.util.SquareStatus;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.battleship.util.Constant.TABLE_DIMENSIONS;
//The Board class has a Square[][] ocean field. This contains the squares that the board consists of.
//        The Board class has an isPlacementOk() method that verifies if placement of ship is possible.

public class Board {
    Square[][] ocean;
    List<List<Integer>> shipsAmbientSquares = new ArrayList<>();
    List<Ship> shipList;

    public Board(List<Ship> shipList) {
        this.shipList = shipList;
        this.ocean = new Square[TABLE_DIMENSIONS][TABLE_DIMENSIONS];
        for (int i = 0; i < TABLE_DIMENSIONS; i++) {
            for (int j = 0; j < TABLE_DIMENSIONS; j++) {
                ocean[i][j] = new Square(i, j, SquareStatus.EMPTY);
            }
        }
    }

    public void putShipsOnTheBoard() {
        for (Ship vessel : shipList
        ) {
            for (Square square : vessel.getShipsSquares()
            ) {
                getOcean()[square.getX()][square.getY()] = square;
            }
        }
    }

    public void putOneShipOnTheBoard(Ship ship) {
        for (Square square : ship.getShipsSquares()
        ) {
            getOcean()[square.getX()][square.getY()] = square;
        }
    }


    public boolean isPlacementOk(List<Integer> coordinates) {
        return getOcean()[coordinates.get(0)][coordinates.get(1)].getSquareStatus() == SquareStatus.EMPTY;
    }

    public Square[][] getOcean() {
        return ocean;
    }

    public void putAmbientSquaresOnTheTable(List<List<Integer>> tempAmbientCoordinates) {
        for (List<Integer> ambientSquare : tempAmbientCoordinates
        ) {
            getOcean()[ambientSquare.get(0)][ambientSquare.get(1)] = new Square(ambientSquare.get(0),
                    ambientSquare.get(1), SquareStatus.AMBIENT);
        }
    }

    @Override
    public String toString() {
        String boardToString = "";
        for (int i = 0; i < ocean.length; i++) {
            for (int j = 0; j < ocean.length; j++) {
                boardToString += ocean[i][j].getSquareStatus() + ", ";
            }
            boardToString += ";\n";
        }
        return boardToString;
    }

    public List<List<Integer>> getShipsAmbientSquares() {
        return shipsAmbientSquares;
    }

    public List<Ship> getShipList() {
        return shipList;
    }
}
