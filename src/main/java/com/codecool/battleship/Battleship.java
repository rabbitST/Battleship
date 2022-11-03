package com.codecool.battleship;

import com.codecool.battleship.gui.Display;
import com.codecool.battleship.util.Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Battleship {

    Display display;
    Input input;
    Game game;
    String[] gameMode = new String[2];

    public Battleship() {
        input = new Input();
        display = new Display(10, 10, input);

        display.getMainMenu().getButtonStartGame().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getGameModeMenu().setVisible(true);
                display.getGameModeMenu().getButtonContainer().setVisible(true);
                display.getMainMenu().setVisible(false);
            }
        });

        display.getGameModeMenu().getChooseGameMode().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game = new Game(display, input, gameMode);
                System.out.println(Arrays.toString(getGameMode()));
                display.getGameModeMenu().getGameModeInputFromUser(gameMode, game.getPlayer1().getShipList());
            }
        });

        display.getShipPlacementBoardPlayer1().getGenerateFleet().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getShipPlacementBoardPlayer1().drawFleetOnTable(game.getPlayer1().getBoard());
                display.getShipPlacementBoardPlayer1().getStartGame().setEnabled(true);
            }
        });

        display.getShipPlacementBoardPlayer2().getGenerateFleet().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getShipPlacementBoardPlayer2().drawFleetOnTable(game.getPlayer2().getBoard());
                display.getShipPlacementBoardPlayer2().getStartGame().setEnabled(true);
            }
        });

        display.getShipPlacementBoardPlayer2().getStartGame().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getShipPlacementBoardPlayer2().setVisible(false);
                display.getGameBoard().setVisible(true);
                game.play();
            }
        });





        //The Battleship class has a loop in which the program runs.
        //The Battleship class displays the main menu and allows the user to a start new game, display high scores, and exit.
    }

    public String[] getGameMode() {
        return gameMode;
    }

    public Display getDisplay() {
        return display;
    }

    public Input getInput() {
        return input;
    }

    public Game getGame() {
        return game;
    }
}
