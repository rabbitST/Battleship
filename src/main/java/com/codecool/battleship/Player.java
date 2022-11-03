package com.codecool.battleship;

//The Player class has a logic responsible for handling shots.
//        The Player class has a field of the List<Ship> type.
//        The Player class has an isAlive method that checks whether the player lost all ships and returns true or false accordingly.


import com.codecool.battleship.util.Constant;
import com.codecool.battleship.util.ShipType;
import com.codecool.battleship.util.SquareStatus;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<Ship> shipList;
    BoardFactory boardFactory;
    Board board;
    String name;
    int points;

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
        for (int j = shipList.size() - 1; j >= 0; j--) {
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

    public void handlingShots(Player player, int[] actualMove) {
        addPoints(Constant.POINTS_FOR_HIT);
        Ship ship = isTargetedShipSank(player, actualMove);
        if (ship != null) addPoints(ship.getShipType().getLengthInSquare() * Constant.POINTS_FOR_SINK_SHIP);
    }

    public boolean isAlive(Player player) {

        for (Ship ship : player.getShipList()
        ) {
            for (Square square : ship.getShipsSquares()
            ) {
                if (square.getSquareStatus() == SquareStatus.SHIP) return true;
            }
        }
        return false;
    }

    public Ship isTargetedShipSank(Player player, int[] coordinates) {
        for (Ship ship : player.getShipList()
        ) {
            boolean isSank = true;
            boolean targetedShip = false;
            for (Square square : ship.getShipsSquares()
            ) {
                if (square.getX() == coordinates[0] && square.getY() == coordinates[1]) {
                    targetedShip = true;
                }
                if (square.getSquareStatus() == SquareStatus.SHIP) isSank = false;
            }
            if (isSank && targetedShip) return ship;
        }
        return null;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoint(int points) {
        this.points = points;
    }

    public void addPoints(int plus) {
        points += plus;
    }

    @Override
    public String toString() {
        return "Player{" +
                "shipList=" + shipList +
                '}';
    }
}
