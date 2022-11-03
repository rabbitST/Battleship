package com.codecool.battleship;

//The Ship class has a List<Square> field. It contains the squares where the ship is located.


import com.codecool.battleship.util.ShipType;

import java.util.List;

public class Ship {
    private List<Square> shipsSquares;
    private ShipType shipType;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
    }

    public List<Square> getShipsSquares() {
        return shipsSquares;
    }

    public void setShipsSquares(List<Square> shipsSquares) {
        this.shipsSquares = shipsSquares;
    }

    public ShipType getShipType() {
        return shipType;
    }


    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }



    @Override
    public String toString() {
        return "Ship{" +
                "shipsSquares=" + shipsSquares +
                ", shipType=" + shipType +
                "}\n";
    }
}
