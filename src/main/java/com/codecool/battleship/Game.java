package com.codecool.battleship;

import com.codecool.battleship.gui.Display;
import com.codecool.battleship.util.Input;
import com.codecool.battleship.util.SquareStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static com.codecool.battleship.util.Constant.FIELD_SIZE_IN_PIXEL;
import static com.codecool.battleship.util.Constant.GUI_PLAYER_INPUT_SWITCH;

//The Game class has a loop in which players make moves.
//        The Game class has a logic which determines the flow of round.
//        The Game class has a condition on which game ends.
//        The Game class provides different game modes which use different round flows.
public class Game {
    Display display;
    Input input;
    Player player1, player2;
    Board board;
    String[] gameMode;

    public Game(Display display, Input input, String[] gameMode) {
        this.display = display;
        this.input = input;
        this.gameMode = gameMode;
        this.player1 = new Player();
        this.player2 = new Player();
    }

    public void play() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                getDisplay().getFieldMove().requestFocus();
            }
        });
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidMove()) {
                    int[] actualMove = getInput().inputToArrayIndex(getDisplay().getFieldMove().getText());
                    if (getDisplay().getFieldMove().getX() < GUI_PLAYER_INPUT_SWITCH) {
                        playerMove(player1, actualMove);
                    } else {
                        playerMove(player2, actualMove);
                    }
                    getDisplay().getFieldMove().setText("");
                    getDisplay().moveInputPanel();
                } else {
                    getDisplay().getFieldMove().setText("");
                    //error message label
                }
            }
        };
        getDisplay().getFieldMove().addActionListener(action);
    }

    public void playerMove(Player player, int[] actualMove) {
        if (player.getBoard().getOcean()[actualMove[0]][actualMove[1]].getSquareStatus().equals(SquareStatus.SHIP)) {
            displayMove(SquareStatus.HIT, actualMove, player);
            player.getBoard().getOcean()[actualMove[0]][actualMove[1]].setSquareStatus(SquareStatus.HIT);
        } else {
            displayMove(SquareStatus.MISSED, actualMove, player);
            player.getBoard().getOcean()[actualMove[0]][actualMove[1]].setSquareStatus(SquareStatus.MISSED);
        }
    }

    private void displayMove(SquareStatus squareStatus, int[] actualMove, Player player) {
        JLabel hit = new JLabel(new ImageIcon(squareStatus.getIcon()));
        createJlabel(hit, actualMove);
        if (player == player1) {
            getDisplay().getPlayer1Board().add(hit, Integer.valueOf(5));
        } else {
            getDisplay().getPlayer2Board().add(hit, Integer.valueOf(5));
        }
    }

    public void createJlabel(JLabel jLabel, int[] actualMove) {
        jLabel.setBounds(getInput().moveToPositionInPixel(actualMove[1]),
                getInput().moveToPositionInPixel(actualMove[0])
                , FIELD_SIZE_IN_PIXEL
                , FIELD_SIZE_IN_PIXEL);
        jLabel.setVisible(true);
    }

    public boolean isValidMove(){
        if(!getInput().isValidCoordinate(getDisplay().getFieldMove().getText()))
            return false;
        int[] actualMove = getInput().inputToArrayIndex(getDisplay().getFieldMove().getText());
        if (getDisplay().getFieldMove().getX()<GUI_PLAYER_INPUT_SWITCH){
            if(!player1.getBoard().getOcean()[actualMove[0]][actualMove[1]].getSquareStatus().equals(SquareStatus.MISSED) && !player1.getBoard().getOcean()[actualMove[0]][actualMove[1]].getSquareStatus().equals(SquareStatus.HIT)) return true;
        }
        else{
            if(!player2.getBoard().getOcean()[actualMove[0]][actualMove[1]].getSquareStatus().equals(SquareStatus.MISSED) && !player2.getBoard().getOcean()[actualMove[0]][actualMove[1]].getSquareStatus().equals(SquareStatus.HIT)) return true;
        }
        return false;
    }

    public Display getDisplay() {
        return display;
    }

    public Input getInput() {
        return input;
    }

    public String[] getGameMode() {
        return gameMode;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
