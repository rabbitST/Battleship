package com.codecool.battleship.util;
import com.codecool.battleship.util.Constant;

//The SquareStatus enum represents possible square statuses (empty, ship, hit, missed).
//        Each SquareStatus has a unicode character that can be used for printing it out. This unicode character is returned by a SquareStatus.GetCharacter() method.

public enum SquareStatus {
    EMPTY(' ',"EMPTY"),
    SHIP('S',"src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png"),
    HIT('H',"src/main/java/com/codecool/battleship/iconsOfComponents/IconHIT.png"),
    MISSED('M',"src/main/java/com/codecool/battleship/iconsOfComponents/iconMISSED.png"),
    AMBIENT('A',"Ambient");

    private final char Character;
    private final String icon;

    SquareStatus(char character, String icon) {
        Character = character;
        this.icon = icon;
    }

    public char getCharacter() {
        return Character;
    }

    public String getIcon() {
        return icon;
    }
}
