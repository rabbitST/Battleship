package com.codecool.battleship;

//The Player class has a logic responsible for handling shots.
//        The Player class has a field of the List<Ship> type.
//        The Player class has an isAlive method that checks whether the player lost all ships and returns true or false accordingly.


import com.codecool.battleship.util.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<Ship> shipList;
    BoardFactory boardFactory;
    Board board;

    public Player() {
        shipList = new ArrayList<>();
        board = new Board(shipList);
        boardFactory = new BoardFactory(board, shipList);
        createFleet();
        setRandomCoordinates();
        //getBoard().putShipsOnTheBoard();
        System.out.println(getBoard());

    }

    private void setRandomCoordinates() {
        int i = 0;
        for (int j = shipList.size()-1; j >=0 ; j--) {
            boardFactory.randomPlacement(board, shipList.get(j));
        }
    }

    private void createFleet() {
        for (ShipType shipType : ShipType.values()
        ) {
            for (int i = 0; i < shipType.getInstancesToGenerate(); i++) {
                shipList.add(new Ship(shipType));
            }
        }
    }

    public void handlingShots() {

    }

    public boolean isAlive() {
        //                        int shipLeft = 0;
//                        for (Square[] squareArray : player1.getBoard().getOcean()
//                        ) {
//                            for (Square square : squareArray
//                            ) {
//                                if (square.getSquareStatus() == SquareStatus.SHIP)shipLeft++;
//                            }
//                        }
//                        System.out.println(shipLeft);
        return true;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Player{" +
                "shipList=" + shipList +
                '}';
    }
}
