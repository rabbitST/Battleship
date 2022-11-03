package com.codecool.battleship;

import com.codecool.battleship.util.Input;
import com.codecool.battleship.util.ShipOrientation;
import com.codecool.battleship.util.SquareStatus;
import static com.codecool.battleship.util.Constant.*;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    Input input = new Input();
    List<Ship> shipList;
    Board board;

    public BoardFactory(Board board, List<Ship> shipList) {
        this.shipList = shipList;
        this.board = board;
    }


    public void randomPlacement(Board board, Ship ship) {
        generateCoordinates(board, ship);
    }

    private void generateCoordinates(Board board, Ship ship) {
        List<List<Integer>> tempShipcoordinates = new ArrayList<>();
        List<List<Integer>> tempAmbientCoordinates = new ArrayList<>();
        generateShipCoordinates(board, ship, tempShipcoordinates, tempAmbientCoordinates);
        setActualShipCoordinates(tempShipcoordinates, ship);
        getBoard().putOneShipOnTheBoard(ship);
        board.putAmbientSquaresOnTheTable(tempAmbientCoordinates);
    }

    private void generateShipCoordinates(Board board, Ship ship, List<List<Integer>> tempShipcoordinates, List<List<Integer>> tempAmbientCoordinates) {
        boolean isFreePosition;
        ShipOrientation shipOrientation = null;
        do {
            isFreePosition = true;
            shipOrientation = ShipOrientation.values()[input.createRandom(2)];
            List<Integer> newStartSquareForShip = new ArrayList<>();
            tempShipcoordinates.clear();
            generateShipStartSquare(board, ship, newStartSquareForShip);
            tempShipcoordinates.add(new ArrayList<>() {{
                add(newStartSquareForShip.get(0));
                add(newStartSquareForShip.get(1));
            }});
            for (int i = 1; i < ship.getShipType().getLengthInSquare(); i++) {
                generateShipsBody(tempShipcoordinates, shipOrientation);
                if (!board.isPlacementOk(tempShipcoordinates.get(tempShipcoordinates.size() - 1))) {
                    isFreePosition = false;
                    shipOrientation = null;
                }
            }
        } while (!isFreePosition);
        if (shipOrientation == ShipOrientation.HORIZONTAL)
            calculateAmbientSquares(tempShipcoordinates, tempAmbientCoordinates,HORIZONTAL_AMBIENT_MATRIX);
        else calculateAmbientSquares(tempShipcoordinates, tempAmbientCoordinates,VERTICAL_AMBIENT_MATRIX);
    }

    private static void generateShipsBody(List<List<Integer>> tempShipcoordinates, ShipOrientation shipOrientation) {
        int[] newCoordinate=new int[2];
        int xCorrection = shipOrientation == ShipOrientation.HORIZONTAL ? 0 : 1;
        int yCorrection = shipOrientation == ShipOrientation.HORIZONTAL ? 1 : 0;
        newCoordinate[0] = tempShipcoordinates.get(tempShipcoordinates.size() - 1).get(0) + xCorrection;
        newCoordinate[1] = tempShipcoordinates.get(tempShipcoordinates.size() - 1).get(1) + yCorrection;
        tempShipcoordinates.add(new ArrayList<>() {{
            add(newCoordinate[0]);
            add(newCoordinate[1]);
        }});
    }

    private void generateShipStartSquare(Board board, Ship ship, List<Integer> newStartSquareForShip) {
        do {
            newStartSquareForShip.clear();
            newStartSquareForShip.add(input.createRandom(10 - (ship.getShipType().getLengthInSquare())));
            newStartSquareForShip.add(input.createRandom(10 - (ship.getShipType().getLengthInSquare())));
        }
        while (!board.isPlacementOk(newStartSquareForShip));
    }

    private void setActualShipCoordinates(List<List<Integer>> tempShipcoordinates, Ship ship) {
        List<Square> shipSquares = new ArrayList<>();
        for (List<Integer> coordinates : tempShipcoordinates
        ) {
            shipSquares.add(new Square(coordinates.get(0), coordinates.get(1), SquareStatus.SHIP));
        }
        ship.setShipsSquares(shipSquares);
    }

    public void calculateAmbientSquares(List<List<Integer>> tempShipcoordinates,
                                                  List<List<Integer>> tempAmbientCoordinates,int[][] ambientMatrix) {

        for (int i = 0; i < 3; i++) {
            int newAmbientSquaresX = tempShipcoordinates.get(0).get(0) + ambientMatrix[i][0];
            int newAmbientSquaresY = tempShipcoordinates.get(0).get(1) + ambientMatrix[i][1];
            if (newAmbientSquaresX != -1 && newAmbientSquaresY != -1) {
                tempAmbientCoordinates.add(new ArrayList<>() {{
                    add(newAmbientSquaresX);
                    add(newAmbientSquaresY);
                }});
            }
        }

        for (int j = 0; j < tempShipcoordinates.size(); j++) {
            for (int i = 3; i < 5; i++) {
                int newAmbientSquaresX = tempShipcoordinates.get(j).get(0) + ambientMatrix[i][0];
                int newAmbientSquaresY = tempShipcoordinates.get(j).get(1) + ambientMatrix[i][1];
                if (newAmbientSquaresX != -1 && newAmbientSquaresY != -1) {
                    tempAmbientCoordinates.add(new ArrayList<>() {{
                        add(newAmbientSquaresX);
                        add(newAmbientSquaresY);
                    }});
                }
            }
        }

        for (int i = 5; i < ambientMatrix.length; i++) {
            int newAmbientSquaresX =
                    tempShipcoordinates.get(tempShipcoordinates.size() - 1).get(0) + ambientMatrix[i][0];
            int newAmbientSquaresY =
                    tempShipcoordinates.get(tempShipcoordinates.size() - 1).get(1) + ambientMatrix[i][1];
            if (newAmbientSquaresX != -1 && newAmbientSquaresY != -1) {
                tempAmbientCoordinates.add(new ArrayList<>() {{
                    add(newAmbientSquaresX);
                    add(newAmbientSquaresY);
                }});
            }
        }
    }

    public void manualPlacement() {

    }

    public Input getInput() {
        return input;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public Board getBoard() {
        return board;
    }
}
