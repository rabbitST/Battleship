package com.codecool.battleship.gui;

import com.codecool.battleship.Board;
import com.codecool.battleship.Game;
import com.codecool.battleship.util.SquareStatus;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static com.codecool.battleship.util.Constant.BOARD_COLOR;

public class ShipPlacementBoardPlayer2 extends JLayeredPane {

    private final int DIMENSION = 10;
    Display display;
    private JLayeredPane placingContainer;
    private JLabel placingBoard, placingPositionContainer, playerID, carrierLabel,battleshipLabel,cruiserLabel,submarineLabel,destroyerLabel;
    private JTextField placingPositionInput;
    private JRadioButton orientationHorizontal, orientationVertical;
    private JButton generateFleet, startGame;

    public ShipPlacementBoardPlayer2(Display display) {

        this.setBounds(0, 0, 1600, 800);
        this.setVisible(false);
        this.setLayout(null);

        placingBoard = new JLabel();
        placingBoard.setBounds(640, 50, 660, 660);
        placingBoard.setVisible(true);
        placingBoard.setLayout(null);


        JLabel backGround = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/shipsBckgd.png"));
        backGround.setBounds(0, 0, 1600, 800);
        add(backGround, Integer.valueOf(0));

        placingPositionContainer = new JLabel();
        placingPositionContainer.setBounds(240, 50, 400, 660);
        placingPositionContainer.setFont(new Font("Lucida", Font.BOLD, 26));
        placingPositionContainer.setLayout(null);
        placingPositionContainer.setBackground(Color.cyan);
        placingPositionContainer.setForeground(Color.WHITE);
        placingPositionContainer.setOpaque(true);
        placingPositionContainer.setVisible(true);



        playerID = new JLabel("Player2");
        playerID.setBounds(25, 35, 300, 50);
        playerID.setFont(new Font("Lucida", Font.BOLD, 40));
        playerID.setLayout(null);
        playerID.setBackground(Color.cyan);
        playerID.setForeground(Color.WHITE);

        int fontSize = 24;
        int width = 100;

        generateFleet = new JButton("Generate My Fleet");
        generateFleet.setBounds(5, 100, 200, 30);
        generateFleet.setFont(new Font("Lucida", Font.BOLD, 20));
        generateFleet.setMargin(new Insets(2, 2, 2, 2));

        startGame = new JButton("Let's play!");
        startGame.setEnabled(false);
        startGame.setBounds(245, 100, 150, 30);
        startGame.setFont(new Font("Lucida", Font.BOLD, 20));
        startGame.setMargin(new Insets(2, 2, 2, 2));

        carrierLabel = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/iconCarrier.png"));
        carrierLabel.setBounds(-30, 150, 300, 100);
        carrierLabel.setFont(new Font("Lucida", Font.BOLD, 26));
        carrierLabel.setBackground(Color.cyan);
        carrierLabel.setForeground(Color.WHITE);

        battleshipLabel = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/iconBattleship.png"));
        battleshipLabel.setBounds(-30, 240, 300, 100);
        battleshipLabel.setFont(new Font("Lucida", Font.BOLD, 26));
        battleshipLabel.setForeground(Color.WHITE);
        battleshipLabel.setLayout(null);

        cruiserLabel = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents" +
                "/iconCruiser" +
                ".png"));
        cruiserLabel.setBounds(-60, 330, 300, 100);
        cruiserLabel.setFont(new Font("Lucida", Font.BOLD, 26));
        cruiserLabel.setLayout(null);
        cruiserLabel.setBackground(Color.cyan);
        cruiserLabel.setForeground(Color.WHITE);

        submarineLabel = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents" +
                "/iconSubmarine" +
                ".png"));
        submarineLabel.setBounds(-60, 420, 300, 100);
        submarineLabel.setFont(new Font("Lucida", Font.BOLD, 26));
        submarineLabel.setLayout(null);
        submarineLabel.setBackground(Color.cyan);
        submarineLabel.setForeground(Color.WHITE);

        destroyerLabel = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents" +
                "/iconDestroyer" +
                ".png"));
        destroyerLabel.setBounds(-70, 510, 300, 100);
        destroyerLabel.setFont(new Font("Lucida", Font.BOLD, 26));
        destroyerLabel.setLayout(null);
        destroyerLabel.setBackground(Color.cyan);
        destroyerLabel.setForeground(Color.WHITE);



        placingPositionContainer.add(playerID, Integer.valueOf(2));
        placingPositionContainer.add(generateFleet, Integer.valueOf(2));
        placingPositionContainer.add(startGame, Integer.valueOf(2));
        placingPositionContainer.add(carrierLabel, Integer.valueOf(2));
        placingPositionContainer.add(battleshipLabel, Integer.valueOf(2));
        placingPositionContainer.add(cruiserLabel, Integer.valueOf(2));
        placingPositionContainer.add(submarineLabel, Integer.valueOf(2));
        placingPositionContainer.add(destroyerLabel, Integer.valueOf(2));
        JLabel PPCbackground = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/backgroundPlacementSidebar.png"));
        PPCbackground.setBounds(0, 0, 400, 660);
        placingPositionContainer.add(PPCbackground, Integer.valueOf(1));
        add(placingPositionContainer, Integer.valueOf(1));
        add(placingBoard, Integer.valueOf(1));

        JButton backMainMenu = new JButton("Main menu");
        backMainMenu.setBounds(540, 300, 150, 30);
        backMainMenu.setFont(new Font("Lucida", Font.BOLD, 20));
        backMainMenu.setMargin(new Insets(2, 2, 2, 2));
    }

    public void drawFleetOnTable(Board board) {
        Border whiteBorder = BorderFactory.createLineBorder(Color.white, 1);
        JLabel label;
        for (int i = 0; i <= DIMENSION; i++) {
            for (int j = 0; j <= DIMENSION; j++) {
                if (i == 0 && j != 0) {
                    label = new JLabel(Character.toString(j + 64), SwingConstants.CENTER);
                    label.setFont(new Font("Arial", Font.BOLD, 40));
                } else {
                    if (j == 0 && i != 0) {
                        label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
                        label.setFont(new Font("Arial", Font.BOLD, 40));

                    } else {

                        if (i > 0 && j>0 && board.getOcean()[i-1][j-1].getSquareStatus().equals(SquareStatus.SHIP)) {
                            label = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png"));
                        } else {
                            label = new JLabel();
                        }
                    }
                }
                label.setBounds(j * 60, i * 60, 60, 60);
                label.setBackground(BOARD_COLOR);
                label.setBorder(whiteBorder);
                label.setOpaque(true);
                label.setVisible(true);
                drawSquaresOnPlayersBoard(label, placingBoard);
            }
        }
        placingBoard.update(placingBoard.getGraphics());
    }

    public void drawSquaresOnPlayersBoard(JLabel label, JLabel placingBoard) {
        placingBoard.add(label, Integer.valueOf(1));
    }

    public JButton getStartGame() {
        return startGame;
    }

    public JButton getGenerateFleet() {
        return generateFleet;
    }
}
