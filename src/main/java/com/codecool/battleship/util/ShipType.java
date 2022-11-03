package com.codecool.battleship.util;

//The ShipType enum represents ship types. The types are Carrier, Cruiser, Battleship, Submarine, and Destroyer.
//        Each ShipType has a unique length.

public enum ShipType {
    DESTROYER(1,4,"src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png"),
    SUBMARINE(2,3,"src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png"),
    CRUISER(3,2,"src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png"),
    BATTLESHIP(4,1,"src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png"),
    CARRIER(5,1,"src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png");

    private int lengthInSquare;
    private int instancesToGenerate;
    private String graphicalRepresentation;

    ShipType(int lengthInSquare, int instancesToGenerate,String graphicalRepresentation) {
        this.lengthInSquare = lengthInSquare;
        this.instancesToGenerate = instancesToGenerate;
        this.graphicalRepresentation=graphicalRepresentation;
    }

    public int getLengthInSquare() {
        return lengthInSquare;
    }

    public int getInstancesToGenerate() {
        return instancesToGenerate;
    }

    public String getGraphicalRepresentation() {
        return graphicalRepresentation;
    }
}
